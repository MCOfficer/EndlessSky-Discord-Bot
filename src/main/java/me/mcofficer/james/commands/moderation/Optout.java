package me.mcofficer.james.commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.mcofficer.james.Util;
import net.dv8tion.jda.core.entities.Role;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Optout extends Command {

    private final String[] optinRoles;

    public Optout(String[] optinRoles) {
        this.name = "optout";
        this.help = "Removes the user from one or more roles X (, Y, Z). A list of free-to-join roles can be found in the rules.";
        this.arguments = "X [Y Z]";
        this.optinRoles = optinRoles;
    }

    @Override
    protected void execute(CommandEvent event) {
        List<Role> remove = Util.getOptinRolesByQuery(event.getArgs(), event.getGuild(), optinRoles);
        event.getGuild().getController().removeRolesFromMember(event.getMember(), remove).queue(success1 ->
                event.getMessage().addReaction("\uD83D\uDC4C").queue(success2 ->
                        event.getMessage().delete().queueAfter(20, TimeUnit.SECONDS)
                )
        );
    }
}