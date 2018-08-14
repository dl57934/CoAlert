#Client ID 477474679196614658
#Client Secret wFC9-lfQX9BKluyZTFkacUFc-p7D6x7P
#NDc3NDc0Njc5MTk2NjE0NjU4.DlMlhA.8EJGowRMFktjSwsA_3ww6o_VoTs

import discord
from discord.ext import commands
import asyncio

bot = commands.Bot ("?")
@bot.event
async def on_ready():
    print ("Bot online")

@bot.command(pass_context=True)
async def ping(ctx):
    await bot.say("pong")

@bot.command(pass_context=True)
async def 침뱉어(ctx):
    await bot.say("퉷 :sweat_drops:")

@bot.command(pass_context=True)
async def 공지(ctx):
    await bot.say("마 잠시 기다려보쇼")


@bot.command(pass_context=True)
async def hello(ctx):
    await bot.say("Hi :wave:")

bot.run("NDc3NDc0Njc5MTk2NjE0NjU4.DlMlhA.8EJGowRMFktjSwsA_3ww6o_VoTs")

