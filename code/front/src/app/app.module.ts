import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { ROUTES } from './app.routes';
import { HttpUtil } from './util/http.util';
import { EventEmitterConfig } from './config/event-emitter.config';

import { AppComponent } from './app.component';
import { NavComponent } from './pages/nav/nav.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { SidebarComponent } from './pages/sidebar/sidebar.component';
import { UserComponent } from './pages/user/user.component';
import { ProjectComponent } from './pages/project/project.component';
import { UserService } from './service/user.service';
import { TableComponent } from './component/table/table.component';

@NgModule({
    declarations: [
        AppComponent,
        NavComponent,
        LoginComponent,
        HomeComponent,
        SidebarComponent,
        UserComponent,
        ProjectComponent,
        TableComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(ROUTES, {useHash: false})
    ],
    providers: [
        HttpUtil,
        EventEmitterConfig,
        UserService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
