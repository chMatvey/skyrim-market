import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AppState } from './state/app.state';
import { NgxsModule } from '@ngxs/store';
import { isDev } from './app.const';
import { RouterState } from './state/router.state';

const states = [
  AppState,
  RouterState
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxsModule.forRoot(states, {
      developmentMode: isDev
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
