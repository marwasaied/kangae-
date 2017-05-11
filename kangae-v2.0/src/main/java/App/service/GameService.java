package App.service;

import App.model.Course;
import App.model.Game;
import App.repository.CourseRepository;
import App.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepo;
    @Autowired
    private CourseRepository courseRepo;

    public ArrayList<Game> getALLGame() {
        return (ArrayList<Game>) gameRepo.findAll();
    }

    public ArrayList<Game> getGamesInCourse(Course course) {
        return gameRepo.findByCourseId(course.getId());
    }

    public void addGame(Game game) {
        gameRepo.save(game);
    }

    public Game getGame(String id) {
        return gameRepo.findOne(id);
    }

    public Game updateGame(Game game) {
        return gameRepo.save(game);
    }

    public void deleteGame(Game game) {
        gameRepo.delete(game);
    }

    public Game getGameInCourse(String courseName, String gameName) {
        return gameRepo.findByCourseNameAndName(courseName, gameName);
    }

    public ArrayList<Game> getGamesByTeacherEmail(String teacherEmail) {
        return gameRepo.findByCourseTeacherEmail(teacherEmail);
    }

    public boolean isValid(Game game) {
        return gameRepo.findByName(game.getName()) == null;
    }

    public Game copyGame(Game game){
        Game newGame = new Game(game.getName(), game.getDescription(), game.getInstruction(), game.getQuestion(), game.getAnswer(), game.getCourse());
        newGame.setName(newGame.getName() + "-copy " + Integer.toString(game.getNumOfCopies() + 1) );
        //TODO:check if changes
        game.setNumOfCopies(game.getNumOfCopies() + 1);
        return newGame;
    }

    public boolean isExist(long id){
        if(gameRepo.findById(id) !=null){
            return true;
        }
        else {
            return false;
        }
    }
}



