package com.liav.bot.interaction.commands.interfaces;

import com.liav.bot.interaction.commands.Command;
import com.liav.bot.interaction.commands.CommandHandler;
import com.liav.bot.main.Bot;
import com.liav.bot.util.storage.CommandStorage;

import sx.blah.discord.handle.obj.IUser;

/**
 * Functional interface which is used in the constructor for {@link Command}.
 * Specifies what happens when the command is executed by the user.
 * 
 * @author Liav
 * @see StringCommand
 * @see CommandHandler
 * @see CommandStorage#commands
 */
@FunctionalInterface
public interface InteractiveCommand {
	/**
	 * Called when the user {@link Command#execute(String[], IUser) executes a
	 * command} specified in {@link CommandStorage#commands}
	 * 
	 * @param param
	 *            Parameters for the command. If there are no parameters, will
	 *            have a {@code length} of 0.
	 * @param u
	 *            The {@link IUser} who executed the command
	 * @return The string for the bot to reply with.
	 * @see Bot#sendMessage
	 */
	String action(final String[] param, final IUser u);
}
