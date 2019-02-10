package nestedvar.Quiver.util;

import java.util.Random;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Embeds {
    EmbedBuilder embed = new EmbedBuilder();
    Data data = new Data();
    Lang lang = new Lang();

    public int color() {
        Random random = new Random();
        Integer randomColor = random.nextInt(0xffffff + 1);
        return randomColor;
    }
    
    // Bot Welcome Embed
    public EmbedBuilder joinWelcome(GuildJoinEvent event) {
        embed.setColor(color());
        embed.setDescription("Phew, I made it over the ~waves~ intact. Oh, hey there, I'm " + event.getJDA().getSelfUser().getAsMention() + " and I'd like to thank you for adding me to your Discord server, it feels pretty toasty in here. Here's some stuff you should know about me:");
        embed.addField("I'm known as", event.getJDA().getSelfUser().getAsMention(), true);
        embed.addField("My prefix is", data.getPrefix(event.getGuild()), true);
        event.getGuild().getDefaultChannel().sendMessage(embed.build()).queue();
        return embed;
    }

    // Member Join Embed
    public EmbedBuilder guildJoinMember() {
        // TODO
        return embed;
    }

    // Bot Join Embed
    public EmbedBuilder guildJoinBot() {
        // TODO
        return embed;
    }

    // Usage embed
    public EmbedBuilder usage(GuildMessageReceivedEvent event, String command) {
        embed.setTitle(lang.getMessage(event.getGuild(), "usageEmbedTitle").replace("{command}", command.replace(data.getPrefix(event.getGuild()), "")).replace("{prefix}", data.getPrefix(event.getGuild())));
        embed.setDescription(lang.getMessage(event.getGuild(), "setUsage").replace("{prefix}", data.getPrefix(event.getGuild())));
        embed.setFooter(lang.getMessage(event.getGuild(), "name") + " " + lang.getMessage(event.getGuild(), "usageEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
        embed.setColor(0xffc83f);
        return embed;
    }

    // Locale command usage
    public EmbedBuilder localeUsage(GuildMessageReceivedEvent event, String command) {
        embed.setTitle(lang.getMessage(event.getGuild(), "usageEmbedTitle").replace("{command}", command.replace(data.getPrefix(event.getGuild()), "")).replace("{prefix}", data.getPrefix(event.getGuild())));
        embed.setDescription(lang.getMessage(event.getGuild(), "localeUsage").replace("{prefix}", data.getPrefix(event.getGuild())));
        embed.setFooter(lang.getMessage(event.getGuild(), "name") + " " + lang.getMessage(event.getGuild(), "usageEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
        embed.setColor(0xffc83f);
        embed.addField("🇧🇷 " + lang.getMessage(event.getGuild(), "brazil"), "`pt_BR`", true);
        embed.addField("🇪🇺 " + lang.getMessage(event.getGuild(), "europe"), "`en_GB`, `de_DE`", true);
        embed.addField("🇭🇰 " + lang.getMessage(event.getGuild(), "hongKong"), "`en_HK`", true);
        embed.addField("🇯🇵 " + lang.getMessage(event.getGuild(), "japan"), "`ja_JP`", true);
        embed.addField("🇷🇺 " + lang.getMessage(event.getGuild(), "russia"), "`ru_RU`", true);
        embed.addField("🇸🇬 " + lang.getMessage(event.getGuild(), "singapore"), "`en_SG`", true);
        embed.addField("🇿🇦 " + lang.getMessage(event.getGuild(), "southAfrica"), "`en_ZA`", true);
        embed.addField("🇦🇺 " + lang.getMessage(event.getGuild(), "australia"), "`en_AU`", true);
        embed.addField("🇺🇸 " + lang.getMessage(event.getGuild(), "unitedStates"), "`en_US`", true);
        return embed;
    }

    // Prefix set success embed
    public EmbedBuilder prefixSetSuccess(GuildMessageReceivedEvent event) {
        embed.setTitle(lang.getMessage(event.getGuild(), "prefixSetEmbedTitle").replace("{prefix}", data.getPrefix(event.getGuild())));
        embed.setDescription(lang.getMessage(event.getGuild(), "prefixSetEmbedDescription").replace("{prefix}", data.getPrefix(event.getGuild())));
        embed.setColor(0x3fff6f);
        embed.setFooter(lang.getMessage(event.getGuild(), "name") + " " + lang.getMessage(event.getGuild(), "prefixSetEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
        return embed;
    }

    // Locale set success embed
    public EmbedBuilder localeSetSuccess(GuildMessageReceivedEvent event) {
        embed.setTitle(lang.getMessage(event.getGuild(), "localeSetEmbedTitle").replace("{locale}", data.getLocale(event.getGuild())));
        embed.setDescription(lang.getMessage(event.getGuild(), "localeSetEmbedDescription"));
        embed.setColor(0x3fff6f);
        embed.setFooter(lang.getMessage(event.getGuild(), "name") + " " + lang.getMessage(event.getGuild(), "localeSetEmbedFooter"), event.getJDA().getSelfUser().getAvatarUrl());
        return embed;
    }
}