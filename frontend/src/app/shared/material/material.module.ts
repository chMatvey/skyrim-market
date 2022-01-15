import { NgModule } from '@angular/core';
import { MatSelectModule } from '@angular/material/select';
import { MatMenuModule } from '@angular/material/menu';
import { MatDialogModule } from '@angular/material/dialog';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search'
import { MatButtonToggleModule } from '@angular/material/button-toggle'
import { MatBadgeModule } from '@angular/material/badge'
import { MatTabsModule } from '@angular/material/tabs'

@NgModule({
  imports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule,
    NgxMatSelectSearchModule,
    MatButtonToggleModule,
    MatBadgeModule,
    MatTabsModule
  ],
  exports: [
    MatSelectModule,
    MatMenuModule,
    MatDialogModule,
    NgxMatSelectSearchModule,
    MatButtonToggleModule,
    MatBadgeModule,
    MatTabsModule
  ]
})
export class MaterialModule { }
