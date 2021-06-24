import { NgModule } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  imports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule
  ],
  exports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule
  ]
})
export class MaterialModule { }
