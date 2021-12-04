import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { RouterModule } from '@angular/router';
import { RxSubscribeModule } from '@soundng/rx-subscribe';
import { MaterialModule } from '@app/shared/material/material.module';
import { UsernamePipe } from './username/username.pipe';
import { RequiredDirective } from './required/required.directive';
import { BaseComponent } from '@app/shared/base/base.component';
import { ErrorPopupComponent } from './error-popup/error-popup.component';
import { NotificationPopupComponent } from './notification-popup/notification-popup.component';
import { ClosePopupComponent } from './close-popup/close-popup.component';
import { OrderListComponent } from './order-list/order-list.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { OrderInfoComponent } from './order-info/order-info.component';
import { SearchSelectComponent } from './search-select/search-select.component';
import { ForgeryOrderInfoComponent } from './order-info/forgery-order-info/forgery-order-info.component';
import { PickpocketingOrderInfoComponent } from './order-info/pickpocketing-order-info/pickpocketing-order-info.component';
import { SweepOrderInfoComponent } from './order-info/sweep-order-info/sweep-order-info.component';
import { OrderTypePipe } from './order-type/order-type.pipe';

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
    OrderInfoComponent,
    SearchSelectComponent,
    ForgeryOrderInfoComponent,
    PickpocketingOrderInfoComponent,
    SweepOrderInfoComponent,
    OrderTypePipe
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
    OrderInfoComponent,
    SearchSelectComponent
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
