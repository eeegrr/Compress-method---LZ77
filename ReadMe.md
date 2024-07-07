# File Compression and Decompression Program

## Task
Develop a program designed for file compression and decompression. The program should be implemented as a console application (without graphical interfaces). It should handle user errors appropriately.


## Program Structure: UML Class Diagram
### Class `Main`
#### Method `comp`
- Compresses a file using the LZ77 algorithm and saves it as a new compressed file.

#### Method `decomp`
- Decompresses a previously compressed file using the LZ77 algorithm and saves it as a new decompressed file.

#### Method `size`
- Displays a message about the size of the input file in bytes.

#### Method `equal`
- Checks if two files have identical contents.

#### Method `about`
- Displays a message about the authors of the program.

### Class `Dec`
- Similar to the `Com` class, this class contains three attributes: two integers `a` and `b`, and one integer `c`.
- It includes a constructor with three arguments to initialize these attributes with the values passed as arguments to the constructor.
