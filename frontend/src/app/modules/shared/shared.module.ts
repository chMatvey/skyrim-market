import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { RouterModule } from '@angular/router';
import { RxSubscribeModule } from '@soundng/rx-subscribe';
import { MaterialModule } from '@shared/material/material.module';
import { UsernamePipe } from './username/username.pipe';
import { RequiredDirective } from './required/required.directive';

@NgModule({
  declarations: [
    ToolbarComponent,
    UsernamePipe,
    RequiredDirective
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ToolbarComponent,
    RouterModule,
    RxSubscribeModule,
    MaterialModule,
    RequiredDirective
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
