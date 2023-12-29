export function formatDateToYYYYMMDD(dateInput: Date): string | null {
  let date: Date;

  // check if dateInput is already Date type object
  if (dateInput instanceof Date) {
    date = dateInput;
  } else {
    // conversion attempt to Date type
    date = new Date(dateInput);
  }
  // check if conversion was correct
  if (isNaN(date.getTime())) {
    console.error('Invalid date in formatDateToYYYYMMDD');
  }
  // if default date, we don't want this date
  if (date.getFullYear() == 1970 && date.getMonth() == 0 && date.getUTCDate() == 1) {
    return null;
  }

  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0'); // adding 1, because january == 0
  const day = date.getDate().toString().padStart(2, '0');

  return `${year}-${month}-${day}`;
}

