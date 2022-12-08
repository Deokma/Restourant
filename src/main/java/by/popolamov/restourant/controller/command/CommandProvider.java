package by.popolamov.restourant.controller.command;

import by.popolamov.restourant.controller.command.impl.admin.*;
import by.popolamov.restourant.controller.command.impl.general.*;
//import by.popolamov.restourant.controller.command.impl.moveto.admin.MoveToAddGenrePageCommand;
//import by.popolamov.restourant.controller.command.impl.moveto.admin.MoveToAddMovieCoverPageCommand;
//import by.popolamov.restourant.controller.command.impl.moveto.admin.MoveToAddMoviePageCommand;
//import by.popolamov.restourant.controller.command.impl.moveto.admin.MoveToEditMoviePageCommand;
import by.popolamov.restourant.controller.command.impl.moveto.admin.MoveToOrdersPageCommand;
import by.popolamov.restourant.controller.command.impl.moveto.general.MoveToLoginPageCommand;
import by.popolamov.restourant.controller.command.impl.moveto.general.MoveToMainPageCommand;
import by.popolamov.restourant.controller.command.impl.moveto.general.MoveToSignupPageCommand;
import by.popolamov.restourant.controller.command.impl.moveto.user.CartRedirectPageCommand;
import by.popolamov.restourant.controller.command.impl.moveto.user.MoveToAccountPageCommand;
import by.popolamov.restourant.controller.command.impl.moveto.user.MoveToCartPageCommand;
import by.popolamov.restourant.controller.command.impl.user.AddDishToCartCommand;
import by.popolamov.restourant.controller.command.impl.user.CompleteCartCommand;
import by.popolamov.restourant.controller.command.impl.moveto.admin.OrderRedirectPageCommand;
//import by.popolamov.restourant.controller.command.impl.moveto.user.MoveToAddFeedbackPageCommand;
//import by.popolamov.restourant.controller.command.impl.user.AddFeedbackCommand;

import java.util.EnumMap;

/**
 * The class CommandProvider. It helps to find a command with specified name.
 */
public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        addGeneralCommands();
        addUserCommands();
        addAdminCommands();
    }

    /**
     * Gets instance.
     *
     * @return the instance of command provider
     */
    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    /**
     * Gets command.
     *
     * @param commandName the command name
     * @return the command with specified name
     */
    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }

    private void addGeneralCommands() {
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.SIGN_UP, new SignUpCommand());
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.MOVE_TO_MAIN_PAGE, new MoveToMainPageCommand());
        commands.put(CommandType.MOVE_TO_LOGIN_PAGE, new MoveToLoginPageCommand());
        commands.put(CommandType.MOVE_TO_SIGNUP_PAGE, new MoveToSignupPageCommand());
    }

    private void addAdminCommands() {
        //commands.put(CommandType.GET_USERS, new GetUsersCommand());
        commands.put(CommandType.CHANGE_USER_ROLE, new ChangeUserRoleCommand());
        commands.put(CommandType.MOVE_TO_ORDERS_PAGE, new MoveToOrdersPageCommand());
        commands.put(CommandType.MOVE_TO_ACCOUNT_PAGE, new MoveToAccountPageCommand());
        commands.put(CommandType.COMPLETE_ORDER, new CompleteOrderCommand());
        commands.put(CommandType.ORDER_REDIRECT_PAGE, new OrderRedirectPageCommand());
        commands.put(CommandType.DELETE_ORDER, new DeleteOrderCommand());
    }

    private void addUserCommands() {
        commands.put(CommandType.SIGN_OUT, new SignOutCommand());
        commands.put(CommandType.MOVE_TO_CART_PAGE, new MoveToCartPageCommand());
        commands.put(CommandType.MOVE_TO_ACCOUNT_PAGE, new MoveToAccountPageCommand());
        commands.put(CommandType.ADD_DISH_TO_CART, new AddDishToCartCommand());
        commands.put(CommandType.CART_REDIRECT_PAGE, new CartRedirectPageCommand());
        commands.put(CommandType.COMPLETE_CART, new CompleteCartCommand());
    }
}