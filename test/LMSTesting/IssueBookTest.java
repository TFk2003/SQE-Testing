/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LMSTesting;

/**
 *
 * @author hp
 */
import Jframe.DBConnection;
import Jframe.IssueBook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;

public class IssueBookTest {

    @InjectMocks
    private IssueBook issueBook; // Create an instance of IssueBook to test

    @Mock
private ResultSet mockResultSet;

@Mock
private PreparedStatement mockPreparedStatement;

@Mock
private Connection mockConnection; // Mock the ResultSet

    @Before
    public void setUp() throws Exception {
        issueBook = new IssueBook();
        //DBConnection c = mock(DBConnection.class); 
        MockitoAnnotations.openMocks(this); // Initialize mocks
        try (MockedStatic<DBConnection> mockedStatic = mockStatic(DBConnection.class)) {
            mockedStatic.when(DBConnection::getConnection).thenReturn(mockConnection); // Mock DBConnection

            // Mock behavior for mockConnection and mockPreparedStatement
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        }
        
        //when(DBConnection.getConnection()).thenReturn(mockConnection); // Mock DBConnection
        //when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        //try (MockedStatic<DBConnection> mockedStatic = mockStatic(DBConnection.class)) {
        //mockedStatic.when(DBConnection::getConnection).thenReturn(mockConnection); // Mock DBConnection
        //mockConnection = DBConnection.getConnection();
        //mockPreparedStatement = mockConnection.prepareStatement("select * from book_details where book_id = ?");
    //}
    }

    @Test
    public void testGetBookDetails_ValidBookId() throws Exception {
        // Arrange
        issueBook.txt_bookId2.setText("1");
        //mockPreparedStatement.setString(1, issueBook.txt_bookId2.getText());
        
        //when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Simulate book found
        when(mockResultSet.getString("book_id")).thenReturn("1");
        when(mockResultSet.getString("book_name")).thenReturn("Head First Java");
        when(mockResultSet.getString("author")).thenReturn("Kathy Sierra");
        when(mockResultSet.getString("quantity")).thenReturn("4");

        

        // Act
        issueBook.getBookDetails();

        // Assert
        assertEquals("1", issueBook.txt_bookId.getText());
        assertEquals("Head First Java", issueBook.txt_bookname.getText());
        assertEquals("Kathy Sierra", issueBook.txt_author.getText());
        assertEquals("4", issueBook.txt_quantity.getText());
    }

    @Test
    public void testGetBookDetails_InvalidBookId() throws Exception {
        // Arrange
        //when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // Simulate book not found

        // Set input for the test case
        issueBook.txt_bookId2.setText("999");

        // Act
        issueBook.getBookDetails();

        // Assert
        assertEquals("Invalid Book Id", issueBook.txt_bookError1.getText());
    }
    
    @Test
    public void testGetStudentDetails_ValidStudentId() throws Exception {
        // Arrange
        issueBook.txt_studentId1.setText("1");
        //mockPreparedStatement.setString(1, issueBook.txt_bookId2.getText());
        
        //when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Simulate book found
        when(mockResultSet.getString("student_id")).thenReturn("1");
        when(mockResultSet.getString("student_name")).thenReturn("Talha");
        when(mockResultSet.getString("department")).thenReturn("BS SE");
        when(mockResultSet.getString("section")).thenReturn("1");

        

        // Act
        issueBook.getStudentDetails();

        // Assert
        assertEquals("1", issueBook.txt_studentId.getText());
        assertEquals("Talha", issueBook.txt_studentname.getText());
        assertEquals("BS SE", issueBook.txt_department.getText());
        assertEquals("A", issueBook.txt_section.getText());
    }
    
    @Test
    public void testGetStudentDetails_InvalidStudentId() throws Exception {
        // Arrange
        //when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // Simulate book not found

        // Set input for the test case
        issueBook.txt_studentId1.setText("999");

        // Act
        issueBook.getStudentDetails();

        // Assert
        assertEquals("Invalid Student Id", issueBook.txt_StudentError.getText());
    }
}

