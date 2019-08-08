package nestedvar.Quiver.Utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Permissions {
    static final SQLDriver sql = new SQLDriver();

    public static boolean isAdministrator(GuildMessageReceivedEvent event) {
        return event.getMember().hasPermission(Permission.ADMINISTRATOR);
    }
    public static boolean hasMuteOthers(GuildMessageReceivedEvent event) {
        return event.getMember().hasPermission(Permission.VOICE_MUTE_OTHERS);
    }
    public static boolean isBlacklisted(GuildMessageReceivedEvent event) {
        try { 
            Connection conn = sql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `members` WHERE `memberid`=" + event.getMember().getUser().getId());
            
            while(rs.next()){
               if(rs.getBoolean("blacklist_status") == true) {
                   return true;
               };
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}