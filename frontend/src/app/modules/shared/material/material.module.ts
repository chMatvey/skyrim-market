import { NgModule } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';

@NgModule({
  imports: [
    MatSelectModule,
    MatMenuModule
  ],
  exports: [
    MatSelectModule,
    MatMenuModule
  ]
})
export class MaterialModule { }
