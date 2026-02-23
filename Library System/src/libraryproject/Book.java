package libraryproject;

	public class Book { 
	
		private String title, author, genre; 
		private boolean isAvailable; 

	public Book(String title, String author, String genre) { 
			this.title = title;
			this. author = author;
			this. genre = genre;
			isAvailable = true;
}
		public String getTitle() {
			return title;
}
		public String getGenre() {
			return genre;
}
		public boolean isAvailable() {
			return isAvailable;
}
		public void setAvailable(boolean status) {
			isAvailable = status;
}
		public String tostring() {
			return title + " by " + author + " (" + genre +", Level " + (isAvailable ? "Available" : "Chcked Out");
		} 
}    