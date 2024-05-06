import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'customDate'
})
export class CustomDatePipe implements PipeTransform {
  constructor(private datePipe: DatePipe) {}

  transform(value: string): string {
    const dateParts = value.split('T')[0].split('-');
    const year = dateParts[0];
    const month = this.getMonthAbbreviation(Number(dateParts[1]));
    const day = dateParts[2];
    return `${year} ${month} ${day}`;
  }

  getMonthAbbreviation(month: number): string {
    const months = ['jan', 'fév', 'mar', 'avr', 'mai', 'jun', 'jul', 'aoû', 'sep', 'oct', 'nov', 'déc'];
    return months[month - 1];
  }
}
