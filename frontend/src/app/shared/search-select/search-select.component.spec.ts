import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { SearchSelectComponent } from './search-select.component';
import { Entity } from '@models/entity'
import { TestScheduler } from 'rxjs/testing'
import { MatSelectModule } from '@angular/material/select'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search'

describe('SearchSelectComponent', () => {
  let component: SearchSelectComponent;
  let fixture: ComponentFixture<SearchSelectComponent>;
  let scheduler: TestScheduler

  const data: Entity[] = [
    {
      id: 1,
      name: 'Iron Axe'
    },
    {
      id: 2,
      name: 'Dragon Sword'
    }
  ]

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchSelectComponent ],
      imports: [
        FormsModule,
        ReactiveFormsModule,
        MatSelectModule,
        NgxMatSelectSearchModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchSelectComponent);
    component = fixture.componentInstance;
    component.data = data
    fixture.detectChanges();
  });

  beforeEach(() => scheduler = new TestScheduler((actual, expected) => {
    expect(actual).toEqual(expected)
  }))

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create MatSelect', () => {
    expect(component.select).toBeTruthy()
  })

  it('should init data with default values', () => {
    scheduler.run(({expectObservable}) => {
      const expectedMarble = '(a)'
      const expectedData = {a: data}
      expectObservable(component.data$).toBe(expectedMarble, expectedData)
    })
  })

  it('should show search result and suggest new value', fakeAsync(() => {
    const search = 'axe'
    component.filterControl.patchValue(search)
    fixture.detectChanges()

    tick(200) // Wait debounce time

    scheduler.run(({expectObservable}) => {
      const expectedMarble = '(a)'
      const expectedData = {a: [{name: search}, data[0]],}
      expectObservable(component.data$).toBe(expectedMarble, expectedData)
    })
  }))
});
