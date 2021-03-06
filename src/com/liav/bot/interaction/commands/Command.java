package com.liav.bot.interaction.commands;

import com.liav.bot.interaction.commands.CategoryHandler.Category;
import com.liav.bot.interaction.commands.interfaces.InteractiveCommand;
import com.liav.bot.interaction.commands.interfaces.StringCommand;
import com.liav.bot.util.storage.CommandStorage;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.MessageBuilder;

/**
 * Defines a text command for the bot to use. Commands can be send by mentioning
 * the bot or starting your message with !
 * <p>
 * All commands must have a help text to be used in the {@code help} command.
 * <p>
 * Commands will also reply to the person who executed it. If the command
 * replies with a TTS (text to speech) message, it will not @mention the user,
 * simply posting it to the {@link IChannel}.
 * <p>
 * All the commands are statically defined in {@link CommandStorage#commands}
 * for organizational sake.
 * 
 * @author Liav
 *
 * @see CommandHandler
 * @see InteractiveCommand
 */
public class Command {
	private final String name, help, category;
	private final InteractiveCommand action;
	private final boolean tts;

	/**
	 * Returns the name of this command, used in ! or @mention. Additionally,
	 * this is what shows up in the {@code help} command.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;// the space is because there is always space when
					// mentioning
	}

	/**
	 * The {@link InteractiveCommand} which is executed when the command is run.
	 * <p>
	 * <b> It is highly recommended you use {@link #execute(String[], IUser)}
	 * instead of this.</b>
	 * 
	 * @return the action
	 */
	public InteractiveCommand getAction() {
		return action;
	}

	/**
	 * Executes this command with the {@link InteractiveCommand} defined in the
	 * constructor.
	 * <p>
	 * If this {@link Command} uses a {@link StringCommand} instead, it will
	 * automatically overload it.
	 * 
	 * @param param
	 *            The parameters for the command
	 * @param u
	 *            The user who executed the command.
	 * @return The {@code String} to reply to the user with.
	 */
	public String execute(String[] param, IUser u) {
		return action.action(param, u);
	}

	/**
	 * Builds the {@link Command Command's} help text for use in the
	 * {@code help} command.
	 * 
	 * @return The help text as specified in the construcotr.
	 */
	public String getHelpText() {
		return tts ? help + "\n*Sends a TTS message*" : help;
	}

	/**
	 * Overloading constructor, settings TTS to false, and using a
	 * {@link StringCommand} instead of an {@link InteractiveCommand}.
	 * {@link Command Commands} that are purely text based (the only thing it
	 * does is reply with a message) should use this constructor to save space.
	 * 
	 * @param n
	 *            The name for the command. This is what the {@linkplain IUser}
	 *            types in to run the command.
	 * @param h
	 *            This is the help text that appears when the {@code help}
	 *            command is used.
	 * @param cat
	 *            Name of the category that this command is in.
	 * @param a
	 *            The functional interface that gets run when this command is
	 *            executed. {@link StringCommand#action(String[])} takes in the
	 *            command parameters.
	 */
	public Command(String n, String h, String cat, StringCommand a) {
		this(n, h, cat, false, a);
	}

	/**
	 * Overloading constructor, settings TTS to false
	 * 
	 * @param n
	 *            The name for the command. This is what the {@linkplain IUser}
	 *            types in to run the command.
	 * @param h
	 *            This is the help text that appears when the {@code help}
	 *            command is used.
	 * @param cat
	 *            Name of the category that this command is in.
	 * @param a
	 *            The functional interface that gets run when this command is
	 *            executed. {@link InteractiveCommand#action(String[], IUser)}
	 *            takes in the command parameters and the user who executed it.
	 * @see StringCommand
	 * @see StringCommand#getHelpCommand()
	 */
	public Command(String n, String h, String cat, InteractiveCommand a) {
		this(n, h, cat, false, a);
	}

	/**
	 * 
	 * Gets the command {@linkplain CategoryHandler.Category category's} name.
	 * 
	 * @return the category this command belongs to
	 * @see CategoryHandler
	 */
	public String getCategoryName() {
		return category;
	}

	/**
	 * Retrieves this command's {@link Category} from {@link CategoryHandler}
	 * 
	 * @return The category this command belongs to.
	 */
	public Category getCategory() {
		return CategoryHandler.getCategory(category);
	}

	/**
	 * Returns whether the command replies with a TTS (text-to-speech) message.
	 * If so, the bot will not @mention the user.
	 * 
	 * @return whether this returns a TTS message
	 * @see MessageBuilder
	 */
	public boolean isTTS() {
		return tts;
	}

	/**
	 * Default constructor.
	 * 
	 * @param n
	 *            The name for the command. This is what the {@linkplain IUser}
	 *            types in to run the command.
	 * @param h
	 *            This is the help text that appears when the {@code help}
	 *            command is used.
	 * @param cat
	 *            Name of the category that this command is in.
	 * @param tts
	 *            Whether the command returns with a TTS (text-to-speech)
	 *            message. If this is true, the bot will not @mention.
	 * @param a
	 *            The functional interface that gets run when this command is
	 *            executed. {@link InteractiveCommand#action(String[], IUser)}
	 *            takes in the command parameters and the user who executed it.
	 *            {@link StringCommand} extends {@code InteractiveCommand,} so
	 *            it can be used in place here. However, there is also the
	 *            overloading constructor
	 *            {@link Command#Command(String, String, StringCommand)}
	 * @see StringCommand
	 * @see StringCommand#getHelpCommand()
	 */
	public Command(String n, String h, String cat, boolean tts, InteractiveCommand a) {
		this.name = n;
		this.action = a;
		this.help = h;
		this.tts = tts;
		this.category = cat;
	}
}
