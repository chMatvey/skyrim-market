import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppState } from '@state/app.state'
import { isDev } from '@app/app.const'
import { NgxsModule } from '@ngxs/store'
import { NgxsRouterPluginModule } from '@ngxs/router-plugin'
import { JwtInterceptor } from '@interceptors/jwt-interceptor.service';
import { environment } from '../environments/environment'
import { AngularFireModule } from '@angular/fire'
import { AngularFireMessagingModule } from '@angular/fire/messaging'

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    NgxsModule.forRoot([AppState], {
      developmentMode: isDev
    }),
    NgxsRouterPluginModule.forRoot(),
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireMessagingModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
