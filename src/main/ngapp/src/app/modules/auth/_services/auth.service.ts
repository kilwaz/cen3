import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {WebSocketService} from '../../../services/websocket.service';
import {Login} from '../../../wsActions/login';

@Injectable()
export class AuthService {
  constructor(private webSocketService: WebSocketService) {
  }

  // Authentication/Authorization
  login(username: string, password: string): Observable<Login> {
    const loginAction: Login = new Login();
    loginAction.username = username;
    loginAction.password = password;

    return this.webSocketService.sendWithObservable(loginAction) as Observable<Login>;
  }
}
