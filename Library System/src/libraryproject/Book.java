package libraryproject;

	public class Book { 
	
		private String title, author, genre;
		private int copies;

		public Book(String title, String author, String genre, int copies) { 
			this.title = title;
			this.author = author;
			this.genre = genre;
			this.copies = copies;
		}

		public String getTitle() {
			return title;
		}
		
		public String getGenre() {
			return genre;
		}

		public String getAuthor() {
			return author;
		}

		public boolean checkAvailable() {
			if (copies > 0) {
				return true;
			} else {
				return false;
			}
		}

		public String toString() {
			return title + " by " + author + " (" + genre +", Level " + (copies > 0 ? "Available" : "Checked Out");
		} 
}    