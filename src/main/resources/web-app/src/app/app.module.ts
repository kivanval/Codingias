import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './admin-ui/nav/nav.component';
import { AdminMainComponent } from './admin-ui/admin-main/admin-main.component';
import {AdminRegisterPageComponent} from "./admin-ui/admin-register/admin-register-page/admin-register-page.component";
import {AdminRegisterFormComponent} from "./admin-ui/admin-register/admin-register-form/admin-register-form.component";
import {AdminRegisterListComponent} from "./admin-ui/admin-register/admin-register-list/admin-register-list.component";
@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    AdminMainComponent,

    AdminRegisterPageComponent,
    AdminRegisterFormComponent,
    AdminRegisterListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
