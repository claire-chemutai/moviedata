/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedataassesment;

import static moviedataassesment.MovieDataAssesment.readJsonFromUrl;
import org.json.JSONArray;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Claire Chemutai
 */
public class MovieDataAssesmentTest {
    
    public MovieDataAssesmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
   //new commit change 
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of readJsonFromUrl method, of class MovieDataAssesment.
     */
    @Test
    public void testReadJsonFromUrl() throws Exception {


        System.out.println("readJsonFromUrl");
        String url = "https://beep2.cellulant.com:9001/assessment/";
        Boolean expResult = true;
        Boolean results = true;
        String read = MovieDataAssesment.readJsonFromUrl(url);
          System.out.println(read);
        assertNotNull(read);
        
    }

    /**
     * Test of insertMovieTitle method, of class MovieDataAssesment.
     */
    @Test
    public void testInsertMovieTitle() throws Exception {
        System.out.println("insertMovieTitle");
        String jsonString = readJsonFromUrl("https://beep2.cellulant.com:9001/assessment/");
        JSONArray moviesarray=new JSONArray(jsonString);
        MovieDataAssesment instance = new MovieDataAssesment();
        instance.insertMovieTitle(moviesarray);
        
    }

    /**
     * Test of insertMovieGenre method, of class MovieDataAssesment.
     */
    @Test
    public void testInsertMovieGenre() throws Exception {
        System.out.println("insertMovieGenre");
        String jsonString = readJsonFromUrl("https://beep2.cellulant.com:9001/assessment/");
        JSONArray moviesarray=new JSONArray(jsonString); 
        MovieDataAssesment instance = new MovieDataAssesment();
        instance.insertMovieGenre(moviesarray);
        
    }

    /**
     * Test of displayGenres method, of class MovieDataAssesment.
     */
    @Test
    public void testDisplayGenres() throws Exception {
        System.out.println("displayGenres");
        String jsonString = readJsonFromUrl("https://beep2.cellulant.com:9001/assessment/");
        JSONArray moviesarray=new JSONArray(jsonString);
        MovieDataAssesment instance = new MovieDataAssesment();
        instance.displayGenres(moviesarray);
        
    }

    /**
     * Test of main method, of class MovieDataAssesment.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        MovieDataAssesment.main(args);
        
        
    }
    
}
