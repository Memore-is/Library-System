package libraryproject;

	public class Book { 
	
		private String title; 
		private String author;
		private String genre;
		private int readingLevel;
		private boolean isAvailable; 

	public Book(String title, String author, String genre, int readingLevel) { 
			this.title = title;
			this. author = author;
			this. genre = genre;
			this.readingLevel = readingLevel;
			isAvailable = true;
}
		public String getTitle() {
			return title;
}
		public String getGenre() {
			return genre;
}
		public int getReadingLevel() {
			return readingLevel;
}
		public boolean isAvailable() {
			return isAvailable;
}
		public void setAvailable(boolean status) {
			isAvailable = status;
}
		public String tostring() {
			return title + " by " + author + " (" + genre +", Level " + readingLevel + 
						(isAvailable ? "Available" : "Chcked Out");
} 
}    