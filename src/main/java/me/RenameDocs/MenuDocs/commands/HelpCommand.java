package me.RenameDocs.MenuDocs.commands;

import me.duncte123.MenuDocs.Constants;
import me.duncte123.MenuDocs.CommandManager;
import me.duncte123.MenuDocs.objects.ICommand;
import net.dv8tion.jda.core.EmbedBuilder;
import java.awt.Color;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class HelpCommand implements ICommand {

        private final CommandManager manager;

        public HelpCommand(CommandManager manager) {
            this.manager = manager;
        }

        @Override
        public void handle(List<String> args, GuildMessageReceivedEvent event) {

            if(args.isEmpty()) {
                generateAndSendEmbed(event);
                return;
            }
            String joined = String.join("", args);

            ICommand command = manager.getCommand(String.join("", args));

            if(command == null) {
                event.getChannel().sendMessage("The command `" + joined + "` does not exist\n" +
                        "Use `" + Constants.PREFIX + getInvoke() + "` for a list of commands").queue();
                return;
            }

            String message = "Command help for`" + command.getInvoke() + "`\n" + command.getHelp();

            event.getChannel().sendMessage(message).queue();
        }

        private void generateAndSendEmbed(GuildMessageReceivedEvent event) {

            EmbedBuilder builder = new EmbedBuilder()
                    .setTitle("A list of all my commands:")
                    .setFooter("©Copyright 2020 RenameJDA", null)
                    .setColor(new Color(32, 34, 37));
            StringBuilder descriptionBuilder = builder.getDescriptionBuilder();

            manager.getCommands().forEach(
                    (command) -> descriptionBuilder.append('`').append(command.getInvoke()).append("`\n")
            );

            //DATA: Make a permission check to see if the bot and send embeds if not, send plain text
            event.getChannel().sendMessage(builder.build()).queue();
        }


        @Override
        public String getHelp() {
            return "Shows a list of all the commands. \n" +
                    "Usage: `" + Constants.PREFIX + getInvoke() + " [command]`";
        }

        @Override
        public String getInvoke() {
            return "help";
        }
}