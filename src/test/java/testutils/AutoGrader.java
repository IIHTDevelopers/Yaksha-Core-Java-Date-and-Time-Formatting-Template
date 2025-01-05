package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;

public class AutoGrader {

	// Test for Date and Time Formatting and Parsing
	public boolean testDateTimeFormattingAndParsing(String filePath) throws IOException {
		System.out.println("Starting testDateTimeFormattingAndParsing with file: " + filePath);

		// Load participant's file
		File participantFile = new File(filePath); // Path to participant's file
		if (!participantFile.exists()) {
			System.out.println("File does not exist at path: " + filePath);
			return false;
		}

		// Parse the file using JavaParser
		FileInputStream fileInputStream = new FileInputStream(participantFile);
		JavaParser javaParser = new JavaParser();
		CompilationUnit cu;
		try {
			cu = javaParser.parse(fileInputStream).getResult()
					.orElseThrow(() -> new IOException("Failed to parse the Java file"));
		} catch (IOException e) {
			System.out.println("Error parsing the file: " + e.getMessage());
			throw e;
		}

		System.out.println("Parsed the Java file successfully.");

		// Flags to check for each date-time formatting and parsing method
		boolean hasFormat = false;
		boolean hasParse = false;
		boolean hasOfPattern = false;

		// Checking if any required formatting or parsing related methods are used
		System.out.println("------ Checking Date and Time Formatting and Parsing ------");
		for (MethodCallExpr method : cu.findAll(MethodCallExpr.class)) {
			String methodName = method.getNameAsString();
			if (methodName.equals("format")) {
				hasFormat = true;
				System.out.println("✓ Found date-time formatting operation: format");
			} else if (methodName.equals("parse")) {
				hasParse = true;
				System.out.println("✓ Found date-time parsing operation: parse");
			} else if (methodName.equals("ofPattern")) {
				hasOfPattern = true;
				System.out.println("✓ Found date-time operation for pattern: ofPattern");
			}
		}

		// Check if all required methods were used
		boolean allMethodsUsed = hasFormat && hasParse && hasOfPattern;

		// Output the result for date-time formatting and parsing operations
		if (allMethodsUsed) {
			System.out.println("✓ All required date-time formatting and parsing operations are present.");
		} else {
			System.out.println("✘ Missing some date-time formatting and parsing operations.");
		}

		// Test result
		System.out.println("Test result: " + allMethodsUsed);
		return allMethodsUsed;
	}
}
