package me.RenameDocs.MenuDocs.commands;

import me.duncte123.MenuDocs.Constants;
import me.duncte123.MenuDocs.objects.ICommand;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class PingCommand implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Pong!").queue((message) ->
                message.editMessageFormat("Ping is %sms", event.getJDA().getPing()).queue()
        );
    }

    @Override
    public String getHelp() {
        return "Pong!\n" +
               "Usage: `" + Constants.PREFIX + getInvoke() + "`";
    }

    @Override
    public String getInvoke() {
        return "ping";
    }
}
