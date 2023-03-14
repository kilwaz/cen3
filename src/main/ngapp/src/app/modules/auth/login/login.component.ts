// Angular
import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import {AuthService} from '../_services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
// RxJS
import {Observable, Subject} from 'rxjs';
import {finalize, takeUntil, tap} from 'rxjs/operators';
// Store
import {select, Store} from '@ngrx/store';
// import {AppState} from '../../../core/reducers';
// Auth
// import {AuthNoticeService} from '../../../../core/auth';
import {ClarityLoginFailed, ClarityLoginSuccess} from '../_actions/auth.actions';
import {errorMessage} from '../_selectors/auth.selectors';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
  // KeenThemes mock, change it to:
  // defaultAuth = {
  //   email: '',
  //   password: '',
  // };
  defaultAuth: any = {
    email: 'admin@demo.com',
    password: 'demo',
  };
  loginForm: UntypedFormGroup;
  loading = false;
  hasError: boolean;
  returnUrl: string;
  isLoading$: Observable<boolean>;

  private unsubscribe: Subject<any>;

  constructor(
    private router: Router,
    private auth: AuthService,
    // private authNoticeService: AuthNoticeService,
    private translate: TranslateService,
    // private store: Store<AppState>,
    private fb: UntypedFormBuilder,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute
  ) {
    this.unsubscribe = new Subject();
  }

  ngOnInit(): void {
    this.initForm();
    // get return url from route parameters or default to '/'
    this.returnUrl =
        this.route.snapshot.queryParams['returnUrl'.toString()] || '/';
    }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  initForm() {
    this.loginForm = this.fb.group({
      email: [
        this.defaultAuth.email,
        Validators.compose([
          Validators.required,
          Validators.email,
          Validators.minLength(3),
          Validators.maxLength(320), // https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
        ]),
      ],
      password: [
        this.defaultAuth.password,
        Validators.compose([
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(100),
        ]),
      ],
    });
  }

  /**
   * Form Submit
   */
  submit() {
    const controls = this.loginForm.controls;
    /** check form */
    if (this.loginForm.invalid) {
      Object.keys(controls).forEach(controlName =>
        controls[controlName].markAsTouched()
      );
      return;
    }

    this.loading = true;

    const authData = {
      email: controls.email.value,
      password: controls.password.value
    };
    this.auth
      .login(authData.email, authData.password)
      .pipe(
        tap(login => {
          if (login) {
            if (login.acceptedAuth) {
              // this.store.dispatch(new ClarityLoginSuccess({}));
            } else {
              // this.store.dispatch(new ClarityLoginFailed({errorMessage: login.errorMessage}));
            }
          } else {
            // this.authNoticeService.setNotice(this.translate.instant('AUTH.VALIDATION.INVALID_LOGIN'), 'danger');
          }
        }),
        takeUntil(this.unsubscribe),
        finalize(() => {
          this.loading = false;
          this.cdr.markForCheck();
        })
      )
      .subscribe();
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
