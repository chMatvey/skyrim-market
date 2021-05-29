import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BackgroundComponent } from './background/background.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { RouterModule } from '@angular/router';

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
    RouterModule
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ]
})
export class SharedModule { }
