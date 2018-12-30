package com.nestedvariables.dev.Discord.Quiver.events.music;

import java.util.concurrent.TimeUnit;

import com.nestedvariables.dev.Discord.Quiver.events.music.AudioPlayerSendHandler;
import com.nestedvariables.dev.Discord.Quiver.Utils;
import com.nestedvariables.dev.Discord.Quiver.Info;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Play extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "play")) {
            if (event.getMember().getVoiceState().inVoiceChannel() == false) {
                EmbedBuilder noChannel = new EmbedBuilder();

                noChannel.setColor(Info.ERROR_RED);
                noChannel.setDescription(event.getAuthor().getAsMention()
                        + ", you need to join a voice channel before using this command!");
                noChannel.setFooter("Quiver User Invalid Voice State", Info.LOGO);

                event.getChannel().sendMessage(noChannel.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            } else if (args.length < 2 && event.getMessage().getAttachments().isEmpty()) {

                EmbedBuilder nullArgs1 = new EmbedBuilder();

                nullArgs1.setDescription(":white_medium_small_square: You didn't give me a song to play!");
                nullArgs1.setColor(Info.ERROR_RED);
                nullArgs1.setFooter("Quiver Not Enough Arguments", Info.LOGO);

                event.getChannel().sendMessage(nullArgs1.build()).queue((message) -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            } else {

                AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
                AudioPlayer player = playerManager.createPlayer();
                //playerManager.useRemoteNodes("localhost:8080");
                AudioSourceManagers.registerRemoteSources(playerManager);
                TrackScheduler trackScheduler = new TrackScheduler(player);
                player.addListener(trackScheduler);

                event.getGuild().getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player));
               
                if (player.isPaused()) {
                    player.setPaused(false);
                    event.getChannel().sendMessage("Resumed Playback").queue();
                }

                if (event.getGuild().getMemberById(event.getJDA().getSelfUser().getId()).getVoiceState().inVoiceChannel() == false) {

                    event.getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
                    event.getChannel().sendMessage("Joining " + event.getMember().getVoiceState().getChannel().getName().toString()).queue();

                    final String trackUrl;
                    final String url = args[1];

                   if (url.startsWith("<") && url.endsWith(">"))
                        trackUrl = url.substring(1, url.length() - 1);
                    else
                        trackUrl = url;

                    playerManager.loadItem(trackUrl, new AudioLoadResultHandler() {

                        @Override
                        public void trackLoaded(AudioTrack track) {
                            trackScheduler.queue(track);
                            player.playTrack(track);
                        }

                        @Override
                        public void playlistLoaded(AudioPlaylist playlist) {
                            for (AudioTrack track : playlist.getTracks()) {
                                trackScheduler.queue(track);
                                player.playTrack(track);
                            }
                        }

                        @Override
                        public void noMatches() {
                            EmbedBuilder noMatch = new EmbedBuilder();

                            noMatch.setTitle("No matches");
                            noMatch.setColor(Info.ERROR_RED);

                            event.getChannel().sendMessage(noMatch.build()).queue();

                        }

                        @Override
                        public void loadFailed(FriendlyException throwable) {
                            EmbedBuilder loadFail = new EmbedBuilder();

                            loadFail.setTitle("Loading of the song Failed");
                            loadFail.setColor(Info.ERROR_RED);

                            event.getChannel().sendMessage(loadFail.build()).queue();

                        }

                    });
                }

            }
        }

    }

}
