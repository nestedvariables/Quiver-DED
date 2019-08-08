package nestedvar.Quiver.Utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.dv8tion.jda.core.events.guild.GuildJoinEvent;

public class SQLUtils {
    static final SQLDriver sql = new SQLDriver();

    //Decide whether or not the bot has been in a guild if Discord decides to trigger the guild join event randomly
    public static boolean haveIBeenHere(GuildJoinEvent event){
        try{
            Connection conn = sql.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `guilds` WHERE `guildid=" + event.getGuild().getId());
            while(rs.next()){
                return true;
            }
            
        return false;
        } catch(SQLException e){
            return false;
        }

    }

}