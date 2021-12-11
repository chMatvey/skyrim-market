import { NgModule } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search'
import { MatButtonToggleModule } from '@angular/material/button-toggle'
import { MatBadgeModule } from '@angular/material/badge'

@NgModule({
  imports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule,
    NgxMatSelectSearchModule,
    MatButtonToggleModule,
    MatBadgeModule
  ],
  exports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule,
    NgxMatSelectSearchModule,
    MatButtonToggleModule,
    MatBadgeModule
  ]
})
export class MaterialModule { }
