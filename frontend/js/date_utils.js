// Function to format a date string
function formatDate(dateString) {
  const date = new Date(dateString);
  const options = {
    weekday: "long",
    year: "numeric",
    month: "long",
    day: "numeric",
  };
  return date.toLocaleDateString("en-GB", options);
}

// Function to calculate and format the end date
function calculateEndDate(startDate, days) {
  const date = new Date(startDate);
  date.setDate(date.getDate() + days - 1);
  return formatDate(date.toISOString());
}
