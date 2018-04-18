import { Injectable } from '@angular/core';

@Injectable()
export class AuthServiceService {

  constructor() { }
  private token: string;
  private user: { id: number } | null;

  public getUser(): { id: number } | null {
    return this.user || JSON.parse(localStorage.getItem('user'));
  }

  public getToken(): string {
    return this.token || localStorage.getItem('token') || '';
  }

  public setUser(user: { id: number }): void {
    this.user = user;
    localStorage.setItem('user', JSON.stringify(user));
  }

  public setToken(token: string): void {
    this.token = token;
    localStorage.setItem('token', token);
  }
}
