import {Component, OnInit, OnDestroy, ChangeDetectorRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Subscription, Observable} from 'rxjs';
import {first} from 'rxjs/operators';
import {UserModel} from '../../models/user.model';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Login} from "../../../../wsActions/login";
import {select, Store} from "@ngrx/store";
import {username, password, errorMessage} from "../../selectors/auth.selectors";
import {LoginResultState} from "../../reducers/auth.reducers";
import {InputPasswordUpdated, InputUsernameUpdated, SubmitLogin} from "../../actions/auth.actions";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
  // KeenThemes mock, change it to:
  defaultAuth: any = {
    email: 'admin@demo.com',
    password: 'demo',
  };
  loginForm: FormGroup;
  hasError: boolean;
  returnUrl: string;
  isLoading$: Observable<boolean>;

  username$: Observable<string>;
  password$: Observable<string>;
  errorMessage$: Observable<string>;

  // private fields
  private unsubscribe: Subscription[] = []; // Read more: => https://brianflove.com/2016/12/11/anguar-2-unsubscribe-observables/

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private store: Store<LoginResultState>
  ) {
    this.isLoading$ = this.authService.isLoading$;
    // redirect to home if already logged in
    if (this.authService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    this.initForm();
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'.toString()] || '/';
    this.username$ = this.store.pipe(select(username));
    this.password$ = this.store.pipe(select(password));
    this.errorMessage$ = this.store.pipe(select(errorMessage));
  }

  inputFieldUpdate(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target.name === "email") {
      this.store.dispatch(new InputUsernameUpdated({username: target.value}));
    } else if (target.name === "password") {
      this.store.dispatch(new InputPasswordUpdated({password: target.value}));
    }
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  initForm() {
    this.loginForm = this.fb.group({
      email: [
        this.username$,
        Validators.compose([
          Validators.required,
          Validators.email,
          Validators.minLength(3),
          Validators.maxLength(320), // https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
        ]),
      ],
      password: [
        this.password$,
        Validators.compose([
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(100),
        ]),
      ],
    });
  }

  submit() {
    this.store.dispatch(new SubmitLogin({}));
  }

  ngOnDestroy() {
    this.unsubscribe.forEach((sb) => sb.unsubscribe());
  }
}
