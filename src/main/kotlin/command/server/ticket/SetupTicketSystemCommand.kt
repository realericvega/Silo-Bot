package command.server.ticket

import command.ICommand
import dev.kord.common.Color
import dev.kord.common.annotation.KordUnsafe
import dev.kord.common.entity.*
import dev.kord.common.entity.optional.OptionalBoolean
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.rest.builder.message.create.actionRow
import dev.kord.rest.builder.message.create.embed

class SetupTicketSystemCommand : ICommand {

    @OptIn(KordUnsafe::class, dev.kord.common.annotation.KordExperimental::class)
    override fun onCommand(client: Kord) {
        client.on<MessageCreateEvent> {
            val args = BotUtil.getArgs(message.content)
            val eColor = BotUtil.getMainEmbedColor()
            val prefix = BotUtil.getCommandPrefix()

            if (args[0] == "${prefix}setupticketsystem" || args[0] == "${prefix}sts") {
                kord.unsafe.guildMessageChannel(message.getGuild().id, Snowflake(767622445301497886)).createMessage {
                    embed {
                        title = "Create a ticket"
                        description = "Click the button below to create a ticket"
                        color = Color(eColor[0], eColor[1], eColor[2])

                        field {
                            val builder = StringBuilder()

                            builder.append("If you need assistance with one of my plugins,\n")
                            builder.append("feel free to create a ticket and I’ll get back to you as soon as possible.\n")
                            builder.append("However, if your not familiar with any of my plugins as of yet, please check out #recources ,\n")
                            builder.append( "there you will see a list of all the plugins that I’ve uploaded onto SpigotMC.")

                            name = "Info"
                            value = builder.toString()
                            inline = false
                        }
                    }

                    actionRow {
                        interactionButton(ButtonStyle.Secondary, "ticket_creation_button") {
                            emoji = DiscordPartialEmoji(
                                Snowflake(654095081845948429),
                                "Ticket",
                                animated = OptionalBoolean.Missing
                            )
                            label = "Create Ticket"
                        }
                    }
                }

//                    message.getGuild().getChannelOf<GuildMessageChannel>(Snowflake(ticketChannel.toString())).createMessage {
//                        embed {
//                            title = "Create a ticket"
//                            description = "Click the button below to create a ticket"
//                            color = Color(eColor[0], eColor[1], eColor[2])
//                        }
//
//                        actionRow {
//                            interactionButton(ButtonStyle.Primary, "ticket_creation_button") {
//                                label = "Create Ticket"
//                                emoji = DiscordPartialEmoji(Snowflake("e62b930d873735bbede7ae1785d13233.svg"), ":envelope:", OptionalBoolean.Missing)
//                            }
//                        }
//                    }

            } else if (args.size > 2 && args[0] == "${prefix}setticketcategory") {
                message.getChannel().createMessage {
                    embed {
                        title = "Incorrect Usage!"
                        description = "Usage: `!help`"
                        color = Color(208, 0, 0)
                    }
                }
            }
        }
    }
}