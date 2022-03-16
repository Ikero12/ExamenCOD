import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public class Main {

    public static void main(String[] args) {

        /**
         * El Token del bot se consigue mediante la herramienta para desarrolladores de discord que te permite
         * crear un bot.
         */
        final String token = "OTUzNjI5ODE1ODc1OTkzNjIw.YjHW-Q.GvEJVL_dqhGvkK6gvcXRrtd_7mU";     //Especificamos el Token del Bot
        final DiscordClient client = DiscordClient.create(token);                               //Conectamos el bot de discord a nuestro programa mediante el token
        final GatewayDiscordClient gateway = client.login().block();                            //Conecta bot al servidor de discord

        try{
            gateway.on(MessageCreateEvent.class).subscribe(event -> {
                final Message message = event.getMessage();                                         //Recopila mensaje en discord
                if ("!ping".equals(message.getContent())) {                                         //Condicional, si el usuario escribe "!ping" el bot devolverá "Pong!"
                    final MessageChannel channel = message.getChannel().block();
                    channel.createMessage("Pong!").block();
                }
            });

            gateway.onDisconnect().block();
        }catch(NullPointerException e){
            System.out.println("Fallo en compilación de mensaje " + e.getMessage());
        }

    }

    }

