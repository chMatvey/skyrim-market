import { NgModule } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search'
import { MatButtonToggleModule } from '@angular/material/button-toggle'

@NgModule({
  imports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule,
    NgxMatSelectSearchModule,
    MatButtonToggleModule
  ],
  exports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule,
    NgxMatSelectSearchModule,
    MatButtonToggleModule
  ]
})
export class MaterialModule { }
