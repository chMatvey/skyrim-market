import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BackgroundComponent } from './background/background.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { RouterModule } from '@angular/router';
import { RxSubscribeModule } from '@soundng/rx-subscribe';

@NgModule({
  declarations: [
    BackgroundComponent,
    ToolbarComponent
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BackgroundComponent,
    ToolbarComponent,
    RouterModule,
    RxSubscribeModule
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    RxSubscribeModule
  ]
})
export class SharedModule { }
