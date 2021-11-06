import { AfterViewInit, Component, forwardRef, Input, OnInit, Provider, ViewChild } from '@angular/core';
import { ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR } from '@angular/forms'
import { Entity } from '@models/entity'
import { Observable, ReplaySubject } from 'rxjs'
import { BaseComponent } from '@app/shared/base/base.component'
import { debounceTime, take, takeUntil } from 'rxjs/operators'
import { MatSelect } from '@angular/material/select'

const VALUE_ACCESSOR: Provider = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => SearchSelectComponent),
  multi: true
};

@Component({
  selector: 'app-search-select',
  templateUrl: './search-select.component.html',
  styleUrls: ['./search-select.component.scss'],
  providers: [VALUE_ACCESSOR]
})
export class SearchSelectComponent extends BaseComponent implements OnInit, AfterViewInit, ControlValueAccessor {
  valueControl = new FormControl()
  filterControl = new FormControl()

  @ViewChild(MatSelect)
  select: MatSelect;

  private allData: Entity[] = []
  private filteredData$ = new ReplaySubject<Entity[]>(1)

  private newEntity: Entity | null = null

  private onChange = (value: Entity) => {}
  private onTouched = () => {}

  get data(): Entity[] {
    return this.allData
  }

  @Input()
  set data(data: Entity[]) {
    this.allData = [...data]
    this.filteredData$.next(this.allData)
  }

  get data$(): Observable<Entity[]> {
    return this.filteredData$.asObservable()
  }

  ngOnInit(): void {
    this.valueControl.valueChanges
      .pipe(
        takeUntil(this.ngUnsubscribe)
      )
      .subscribe(value => {
        this.onChange(value)
        this.onTouched()
      })

    this.filterControl.valueChanges
      .pipe(
        takeUntil(this.ngUnsubscribe),
        debounceTime(200)
      )
      .subscribe(value => this.filter(value))
  }

  ngAfterViewInit(): void {
    this.filteredData$
      .pipe(
        take(1),
        takeUntil(this.ngUnsubscribe)
      )
      .subscribe(() =>
        this.select.compareWith = (a: Entity, b: Entity) => a?.id === b?.id
      )
  }

  registerOnChange(fn: any): void {
    this.onChange = fn
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn
  }

  setDisabledState(isDisabled: boolean): void {
    isDisabled ? this.valueControl.disable() : this.valueControl.enable()
  }

  writeValue(entity: Entity): void {
    this.valueControl.setValue(entity)
    this.onChange(entity)
    this.onTouched()
  }

  private filter(search: string): void {
    if (search) {
      const parsedSearch = search.toLowerCase()
      const filteredData = this.data.filter(entity => entity.name.toLowerCase().indexOf(parsedSearch) > -1)
      this.newEntity = {name: search}
      this.filteredData$.next([this.newEntity, ...filteredData])
    } else {
      if (this.newEntity) {
        this.filteredData$.next([this.newEntity, ...this.data])
      } else {
        this.filteredData$.next([...this.data])
      }
    }
  }
}
