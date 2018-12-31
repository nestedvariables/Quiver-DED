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
                usage.setTitle(Utils.getMessage(event.getGuild(), "usageEmbedTitle").replace("{command}", args[0].replace(Utils.getPrefix(event.getGuild()), "")).replace("{prefix}", Utils.getPrefix(event.getGuild())));
                usage.setDescription(Utils.getMessage(event.getGuild(), "setUsage").replace("{prefix}", Utils.getPrefix(event.getGuild())));
                usage.setFooter(Utils.getMessage(event.getGuild(), "name") + " " + Utils.getMessage(event.getGuild(), "usageEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
                usage.setColor(Utils.embedColor("usage"));
                event.getChannel().sendMessage(usage.build()).queue();
                usage.clear();
            }
            else if (args[1].equalsIgnoreCase("prefix")) {
                if (args.length < 3) {
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setTitle(Utils.getMessage(event.getGuild(), "usageEmbedTitle").replace("{command}", args[1].replace(Utils.getPrefix(event.getGuild()), "")).replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    usage.setDescription(Utils.getMessage(event.getGuild(), "prefixUsage").replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    usage.setFooter(Utils.getMessage(event.getGuild(), "name") + " " + Utils.getMessage(event.getGuild(), "usageEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
                    usage.setColor(Utils.embedColor("usage"));
                    event.getChannel().sendMessage(usage.build()).queue();
                    usage.clear();
                }
                else {
                    Utils.setPrefix(event.getGuild(), args[2]);
                    EmbedBuilder success = new EmbedBuilder();
                    success.setTitle(Utils.getMessage(event.getGuild(), "prefixSetEmbedTitle").replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    success.setDescription(Utils.getMessage(event.getGuild(), "prefixSetEmbedDescription").replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    success.setColor(Utils.embedColor("success"));
                    success.setFooter(Utils.getMessage(event.getGuild(), "name") + " " + Utils.getMessage(event.getGuild(), "prefixSetEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
                    event.getChannel().sendMessage(success.build()).queue();
                    success.clear();
                }   
            }
            else if (args[1].equalsIgnoreCase("locale")) {
                if (args.length < 3) {
                    EmbedBuilder usage = new EmbedBuilder();
                    usage.setTitle(Utils.getMessage(event.getGuild(), "usageEmbedTitle").replace("{command}", args[1].replace(Utils.getPrefix(event.getGuild()), "")).replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    usage.setDescription(Utils.getMessage(event.getGuild(), "localeUsage").replace("{prefix}", Utils.getPrefix(event.getGuild())));
                    usage.setFooter(Utils.getMessage(event.getGuild(), "name") + " " + Utils.getMessage(event.getGuild(), "usageEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
                    usage.setColor(Utils.embedColor("usage"));
                    usage.addField("🇧🇷 " + Utils.getMessage(event.getGuild(), "brazil"), "`pt_BR`", true);
                    usage.addField("🇪🇺 " + Utils.getMessage(event.getGuild(), "europe"), "`en_GB`, `de_DE`", true);
                    usage.addField("🇭🇰 " + Utils.getMessage(event.getGuild(), "hongKong"), "`en_HK`", true);
                    usage.addField("🇯🇵 " + Utils.getMessage(event.getGuild(), "japan"), "`ja_JP`", true);
                    usage.addField("🇷🇺 " + Utils.getMessage(event.getGuild(), "russia"), "`ru_RU`", true);
                    usage.addField("🇸🇬 " + Utils.getMessage(event.getGuild(), "singapore"), "`en_SG`", true);
                    usage.addField("🇿🇦 " + Utils.getMessage(event.getGuild(), "southAfrica"), "`en_ZA`", true);
                    usage.addField("🇦🇺 " + Utils.getMessage(event.getGuild(), "australia"), "`en_AU`", true);
                    usage.addField("🇺🇸 " + Utils.getMessage(event.getGuild(), "unitedStates"), "`en_US`", true);
                    event.getChannel().sendMessage(usage.build()).queue();
                    usage.clear();
                }
                else {
                    Utils.setLocale(event.getGuild(), args[2]);
                    EmbedBuilder success = new EmbedBuilder();
                    success.setTitle(Utils.getMessage(event.getGuild(), "localeSetEmbedTitle").replace("{locale}", Utils.getLocale(event.getGuild())));
                    success.setDescription(Utils.getMessage(event.getGuild(), "localeSetEmbedDescription"));
                    success.setColor(Utils.embedColor("success"));
                    success.setFooter(Utils.getMessage(event.getGuild(), "name") + " " + Utils.getMessage(event.getGuild(), "localeSetEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
                    event.getChannel().sendMessage(success.build()).queue();
                    success.clear();
                }   
            }
        }
    }
}