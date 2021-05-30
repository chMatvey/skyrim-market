import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NgxsModule } from '@ngxs/store';
import { isDev } from './app.const';
import { HttpClientModule } from '@angular/common/http';
import { AppState } from './state/app.state';
import { NgxsRouterPluginModule } from '@ngxs/router-plugin';

const states = [
  AppState
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxsModule.forRoot(states, {
      developmentMode: isDev
    }),
    NgxsRouterPluginModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
