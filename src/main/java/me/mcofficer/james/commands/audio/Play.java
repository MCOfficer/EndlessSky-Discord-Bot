package me.mcofficer.james.commands.audio;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.mcofficer.james.audio.Audio;

public class Play extends Command {

    private final Audio audio;

    public Play(Audio audio) {
        this.name = "play";
        this.help = "Plays a track by it's url, or searches for it by a query";
        this.arguments = "<query>";

        this.audio = audio;
    }

    @Override
    protected void execute(CommandEvent event) {
        //TODO: search
        audio.connect(event.getMember().getVoiceState().getChannel());
        audio.loadItem(event.getArgs(), event);
    }
}