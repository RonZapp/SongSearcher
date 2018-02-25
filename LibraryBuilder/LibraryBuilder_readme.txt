MusicLibrary objects have the ability to export their contents to a .txt file. 

Output: 
They can take an absolute or relative path to the .txt file. If the .txt file exists at the path, it will check if it is writeable. If there is no .txt file at the path or the file is not writeable, it will attempt to create a new .txt file in its place. If the directory that the .txt file is supposed to be in does not exist, the program will abort exporting.

Order:
The MusicLibrary can export in three orders: artist, title, and tag.
The artist order will write each song on one line ordered alphabetically by artist, then by title, then by trackId. The title order will write each song on one line ordered alphabetically by title, then by artist, then by trackId. The tag order will write each tag on one line along with the trackIds of all the songs labeled with that tag.

Flags:
Pass the desired values for output and order to the Argument Validator in a String[]. This is also where you pass the input value, which is discussed in the main readme. Each value must be preceeded by its accompanying flag. The flags are "-input", "-output", and "-order". Acceptable orders are "artist", "title", and "tag". Example of good values: {"-input", "my_project/input_directory", "-output", "my_project/output_directory/results.txt", "-order", "tag"}. Flags can be in any order, as long as the appropriate value is following the flag.

How to:
The easiest way to build and export a music library is to pass a valid set of arguments as described above into the Driver.libraryPrint() method.