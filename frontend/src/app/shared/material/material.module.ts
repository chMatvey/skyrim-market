import { NgModule } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search'

@NgModule({
  imports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule,
    NgxMatSelectSearchModule
  ],
  exports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule,
    NgxMatSelectSearchModule
  ]
})
export class MaterialModule { }
