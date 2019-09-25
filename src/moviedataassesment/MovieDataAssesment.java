/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedataassesment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author Claire Chemutai
 */
public class MovieDataAssesment {

    /**
     * @param args the command line arguments
     */
    
    private static Connection conn;
    private static Object stmt;

    public  MovieDataAssesment() {
        
    }
    
    /**
    * makes connection to the database
    *  
    */
    private void DBConnection() throws SQLException{
        System.out.println("Connecting to the Database");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/moviedata?user=root&password=root");

        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.err.println(e);
            System.exit(0);
            
        }
    }
    
    
    
   /**
    * Reads the URL and converts to string 
    *
    * @return  the url string 
    */
   public static String readJsonFromUrl(String url) throws IOException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      StringBuilder sb = new StringBuilder();
      String line=null;
      
      while((line=reader.readLine())!=null){
          sb.append(line+"\n");
      }      
      return sb.toString();
    } finally {
      is.close();
    }
  }
   
   
   /**
    * inserts movie title and id to the database
    * moviesarray argument specifies the json array to extract data from
    * 
    */
   public  void insertMovieTitle(JSONArray moviesarray) throws JSONException{
       JSONObject movie = new JSONObject();
       
       //loop through the movies array
            for(int i=0; i<moviesarray.length();i++){
                movie=moviesarray.getJSONObject(i);
                Object title=movie.get("title");
                Object id=movie.get("movieID");
                
                //inserting into the database
                try{
                    PreparedStatement ps;
                    ps = conn.prepareStatement("Insert Into movietitle set  movieID=?,title=?");
                    ps.setObject(1, id);
                    ps.setObject(2, title);

                    ps.execute(); //use execute if no results expected back;
                }catch(SQLException error){
                    System.err.println("Error "+error.toString());
                    return;
                }
            }
       
   }
   
   /**
    * inserts movie genre and id to the database
    * moviesarray argument specifies the json array to extract data from
    * 
    */
    public  void insertMovieGenre(JSONArray moviesarray) throws JSONException{
       JSONObject movie = new JSONObject();
       
       //loop through the movies array
            for(int i=0; i<moviesarray.length();i++){
                movie=moviesarray.getJSONObject(i);
                Object genre=movie.get("genre");
                Object id=movie.get("movieID");
                
                 //inserting into the database
                try{
                    PreparedStatement ps;
                    ps = conn.prepareStatement("Insert Into moviegenre set  movieID=?,movieGenre=?");
                    ps.setObject(1, id);
                    ps.setObject(2, genre);

                    ps.execute(); //use execute if no results expected back;
                }catch(SQLException error){
                    System.err.println("Error "+error.toString());
                    return;
                }
            }
       
   }
    
    
    /**
    * display the genres and the number of movies in each in a tabular form
    * moviesarray argument specifies the json array to extract data from
    * 
    */
     public  void displayGenres(JSONArray moviesarray) throws JSONException{
        
        JSONObject movie = new JSONObject();
        
        //hashmap to store the genre and the number of movies per genre
          HashMap<String,Integer>moviemap = new HashMap();
            for(int i=0; i<moviesarray.length();i++){
                movie=moviesarray.getJSONObject(i);
                String genre=movie.get("genre").toString();
                int movieid=Integer.parseInt(movie.get("movieID").toString());
                ArrayList<String> gnre=new ArrayList<>(Arrays.asList(genre.split("\\|")));
                
                //put the genres and the number of movies per genre in the hashmap
                for(int j=0; j<gnre.size(); j++){
                    if(moviemap.containsKey(gnre.get(j))){
                      moviemap.put(gnre.get(j),moviemap.get(gnre.get(j))+1);
                        
                    }
                    else{
                        moviemap.put(gnre.get(j),1);
                    }
                
                
            }
            
       
            } 
            
            //display table
            for (String movies: moviemap.keySet()){
                String key = movies;
                String value = moviemap.get(movies).toString(); 
                System.out.format("%10s%15s%n", key, value);//formats the table
                
                        
                
    }
   }

   
    /**
    * main method
    */
  public static void main(String[] args) throws IOException, SQLException, JSONException {
        Logger log=Logger.getLogger("MyLogger");
        FileHandler fh;
        
        MovieDataAssesment md=new MovieDataAssesment();
        
        
        try{
            fh=new FileHandler("C:/Users/study/Documents/NetBeansProjects/MovieDataAssesment/ActivityLog.txt", true);
        
            log.addHandler(fh);
            SimpleFormatter format= new SimpleFormatter();
            fh.setFormatter(format);
            
            String jsonString = readJsonFromUrl("https://beep2.cellulant.com:9001/assessment/");
            JSONArray moviesarray=new JSONArray(jsonString); 
            log.info("connect database");//creates connection
            md.DBConnection();
            log.info("insert title");
            md.insertMovieTitle(moviesarray);
            log.info("insert genres");
            md.insertMovieGenre(moviesarray);//inserts into the departments table
            log.info("display genres");
            md.displayGenres(moviesarray); //inserts into the staff table
        
           
        }
         catch (SecurityException e){
             e.printStackTrace();
         } catch (SQLException ex) {
            Logger.getLogger(MovieDataAssesment.class.getName()).log(Level.SEVERE, null, ex);
        }
            catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
            catch(Exception e)
        {
            e.printStackTrace();
        }
    }

      
  }

    

