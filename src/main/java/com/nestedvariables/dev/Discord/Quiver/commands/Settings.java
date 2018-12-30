package com.nestedvariables.dev.Discord.Quiver.commands;

import com.nestedvariables.dev.Discord.Quiver.Utils;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Settings extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Utils.getPrefix(event.getGuild()) + "set")) {
            if (args.length < 2) {
                EmbedBuilder usage = new EmbedBuilder();
                usage.setTitle(Utils.getMessage(event.getChannel(), "usageEmbedTitle").replace("{command}", args[0].replace(Utils.getPrefix(event.getGuild()), "")).replace("{prefix}", Utils.getPrefix(event.getGuild())));
                usage.setDescription(Utils.getMessage(event.getChannel(), "setUsage").replace("{prefix}", Utils.getPrefix(event.getGuild())));
                usage.setFooter(Utils.getMessage(event.getChannel(), "name") + " " + Utils.getMessage(event.getChannel(), "usageEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
                usage.setColor(Utils.embedColor("usage"));
                event.getChannel().sendMessage(usage.build()).queue();
            }
            else if (args[1].equalsIgnoreCase("prefix")) {
                if (args.length < 3) {
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setTitle(Utils.getMessage(event.getChannel(), "usageEmbedTitle").replace("{command}", args[1].replace(Utils.getPrefix(event.getGuild()), "")).replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    usage.setDescription(Utils.getMessage(event.getChannel(), "prefixUsage").replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    usage.setFooter(Utils.getMessage(event.getChannel(), "name") + " " + Utils.getMessage(event.getChannel(), "usageEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
                    usage.setColor(Utils.embedColor("usage"));
                    event.getChannel().sendMessage(usage.build()).queue();
                }
                else {
                    Utils.setPrefix(event.getGuild(), event.getChannel(), args[2]);
                    EmbedBuilder success = new EmbedBuilder();
                    success.setTitle(Utils.getMessage(event.getChannel(), "prefixSetEmbedTitle").replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    success.setDescription(Utils.getMessage(event.getChannel(), "prefixSetEmbedDescription").replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    success.setColor(Utils.embedColor("success"));
                    success.setFooter(Utils.getMessage(event.getChannel(), "name") + " " + Utils.getMessage(event.getChannel(), "prefixSetEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
                    event.getChannel().sendMessage(success.build()).queue();
                    success.clear();
                }   
            }
            else if (args[1].equalsIgnoreCase("locale")) {
                if (args.length < 3) {
                    event.getChannel().sendMessage("Usage: `!set locale 'locale'`").queue();
                }
                else {
                    Utils.setLocale(event.getChannel(), args[2]);
                    event.getChannel().sendMessage(Utils.getMessage(event.getChannel(), "setlocale").replace("{locale}", Utils.getLocale(event.getGuild()))).queue();
                }   
            }
        }
    }
}