package LibraryDatabaseBuilder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

/*
 * Recursively searches directory for files with specified extension
 * @author RonZapp
 * 
 */
public class DirectoryCrawler {
	private static final boolean printCount = true; 	// Determines whether count of files found will be printed
	private static final int increment = 50000; 		// Determines how often to print count
	
	private Path target;					// Directory to begin search
	private String extension;			// Extension of files to look for
	private HashSet<String> contents;	// Contents of files in directory
	private int count;					// Counts number of files found, will remain zero if printCount == false
	
	/* Constructor */
	public DirectoryCrawler(String target, String extension) {
		this.target = Paths.get(target);
		this.extension = extension;
		this.contents = new HashSet<String>(60000);
		this.count = 0;
	}
	
	/*
	 * Crawls through files within target directory
	 * @return Set containing the first line of each file found within target directory with specified extension
	 */
	public HashSet<String> crawl() {
		crawlRecursive(target);
		System.out.printf("Finished crawling directory. Count is %d, returning set of size %d.\n", count, contents.size());
		return contents;
	}
	
	/*
	 * Helper function recursively searches through target directory
	 * @param path Directory to search
	 */
	private void crawlRecursive(Path path) {
		if (Files.isDirectory(path)) {
			try (DirectoryStream<Path> dir = Files.newDirectoryStream(path)) {
				for (Path entry: dir) {
					crawlRecursive(entry);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (path.toString().endsWith(extension)) {
			documentReader(path);
		}
	}
	
	/*
	 * Helper function tries to open document and add first line to this.contents
	 * @ doc Document to read
	 */
	private void documentReader(Path doc) {
		if (Files.isReadable(doc)) {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doc.toString()), "UTF-8"))) {
				contents.add(br.readLine());
				if (printCount) {
					if (count % increment == 0) {
						System.out.printf("%d files read\n", count);
					}
					count++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
