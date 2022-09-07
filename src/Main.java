import Data.Article;
import Data.User;
import Repository.ArticleRepository;
import Repository.UserRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        Article article = new Article();
        UserRepository userRepository = new UserRepository();
        ArticleRepository articleRepository = new ArticleRepository();

        System.out.println("Select your choice");

        int articleChoice;
        int insertOrUpdate;

        do {

            System.out.println("1.Sign In" + " \n2.sign up" + "\n3.show all article" +
                    "\n4.exist");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    //sign in method
                    System.out.println("Enter your username");
                    user.setUsername(scanner.nextLine());
                    System.out.println("Enter your password");
                    user.setPassword(scanner.nextLine());

                    try {
                        if (userRepository.matchPassword(user)){
                            articleRepository.showArticle();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:

                    try {

                        System.out.println("Enter your name : ");
                        user.setUsername(scanner.nextLine());
                        System.out.println("Enter your nationalCode : ");
                        user.setNationalCode(scanner.nextLine());
                        System.out.println("Enter your birthday : ");
                        user.setBirthday(Date.valueOf(scanner.nextLine()));
                        System.out.println("Enter your password : ");
                        user.setPassword(scanner.nextLine());
                        userRepository.signUp(user);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 3:

                    System.out.println("1.show article" + " \n2.update and delete article"
                            + "\n3.insert new article" );
                    articleChoice = Integer.parseInt(scanner.nextLine());

                    switch (articleChoice){
                        case 1:
                            try {
                                if (articleRepository.published()){
                                    articleRepository.showArticle();
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;

                        case 2:

                            System.out.println("1.update article" + " \n2.delete article");
                            insertOrUpdate = Integer.parseInt(scanner.nextLine());

                            switch (insertOrUpdate) {
                                case 1:
                                     try {
                                      articleRepository.updateArticle(article);
                                      } catch (SQLException e) {
                                      throw new RuntimeException(e);
                                      }
                                     break;
                                case 2:
                                try {
                                    articleRepository.delete(article);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                            }
                            break;

                        case 3:
                            try {
                                articleRepository.insertArticle(article);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                    }
                case 4:
                    System.exit(0);
                    break;
            }

        }
        while (choice!=0);
    }
}