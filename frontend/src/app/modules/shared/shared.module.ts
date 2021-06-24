import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { RouterModule } from '@angular/router';
import { RxSubscribeModule } from '@soundng/rx-subscribe';
import { MaterialModule } from '@shared/material/material.module';
import { UsernamePipe } from './username/username.pipe';
import { RequiredDirective } from './required/required.directive';
import { BaseComponent } from '@shared/base/base.component';
import { ErrorPopupComponent } from './error-popup/error-popup.component';
import { NotificationPopupComponent } from './notification-popup/notification-popup.component';
import { ClosePopupComponent } from './close-popup/close-popup.component';
import { OrderListComponent } from './order-list/order-list.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { OrderInfoComponent } from './order-info/order-info.component';

@NgModule({
  declarations: [
    ToolbarComponent,
    UsernamePipe,
    RequiredDirective,
    BaseComponent,
    ErrorPopupComponent,
    NotificationPopupComponent,
    ClosePopupComponent,
    OrderListComponent,
    WelcomeComponent,
    OrderInfoComponent
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ToolbarComponent,
    RouterModule,
    RxSubscribeModule,
    MaterialModule,
    RequiredDirective,
    BaseComponent,
    OrderListComponent,
    OrderInfoComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    RxSubscribeModule,
    MaterialModule
  ]
})
export class SharedModule { }
