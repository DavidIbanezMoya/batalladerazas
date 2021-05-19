drop database if exists project;
create database project;
use project;

create table weapons (
    weapon_id int(3) primary key auto_increment,
    weapon_name varchar(25),
    weapon_image_path varchar(100) not null,
    strength int(2),
    speed int(2),
    weapon_race varchar(200)
);

create table race (
    race_id int(3) primary key auto_increment,
    race_name varchar(20) not null,
    hp int(2),
    strength int(2),
    speed int(2),
    agility int(2),
    defense int(2)
);

create table warriors (
    warrior_id int(3) primary key auto_increment,
    warrior_name varchar(25),
    warrior_image_path varchar(100),
    race_id int(3) not null,
    constraint fk_warrior_race foreign key (race_id) references race (race_id)
);

create table players (
    player_id int(3) primary key auto_increment,
    player_name varchar(25) 
);

create table battle (
    battle_id int(3) primary key auto_increment,
    player_id int(3),
    warrior_id int(3),
    warrior_weapon_id int(3),
    opponent_id int (3),
    opponent_weapon_id int(3),
    injuries_caused int(2),
    injuries_suffered int (2),
    battle_points int (5),
    constraint fk_battle_playerid foreign key (player_id) references players (player_id),
    constraint fk_battle_weaponid foreign key (warrior_weapon_id) references weapons (weapon_id),
    constraint fk_battle_warriorid foreign key (warrior_id) references warriors (warrior_id),
    constraint fk_battle_opponentid foreign key (opponent_id) references warriors (warrior_id),
    constraint fk_battle_opponent_weaponid foreign key (opponent_weapon_id) references weapons (weapon_id)

);

create table ranking (
    player_id int(3),
    player_name varchar(200),
    warrior_id int(3),
    warrior_name varchar (25),
    weapon_name varchar (25),
    puntuation int (5),
    constraint fk_ranking_playerid foreign key (player_id) references players (player_id), 
    constraint fk_ranking_warriorid foreign key (warrior_id) references warriors (warrior_id)
);

insert into race(race_name,hp,strength,defense,agility,speed) values
("Dwarf",60,6,4,5,3),
("Human",50,5,3,6,5),
("Elf",40,4,2,7,7);

insert into warriors(warrior_name,warrior_image_path,race_id) values
("Ornn","images/warriors/ornn.png",1),
("Darius","images/warriors/darius.png",2),
("Ezreal","images/warriors/ezreal.png",3),
("Gragas","images/warriors/gragas.png",1),
("Garen","images/warriors/garen.png",2),
("Talon","images/warriors/talon.png",3),
("Poppy","images/warriors/poppy.png",1),
("Fiora","images/warriors/fiora.png",2),
("Ashe","images/warriors/ashe.png",3),
("Kazuma","images/warriors/kazuma.png",1),
("Sylas","images/warriors/sylas.png",2),
("Taric","images/warriors/taric.png",3);

insert into weapons(weapon_name,weapon_image_path,strength,speed,weapon_race) values
("Dagger","images/weapons/draktar.png",0,3,"Human,Elf"),
("Sword","images/weapons/botr.png",1,1,"Human,Elf,Dwarf"),
("Axe","images/weapons/axe.png",3,0,"Human,Dwarf"),
("Double Sword","images/weapons/doublesword.png",2,2,"Human,Elf"),
("Scimitar","images/weapons/cimitarra.png",1,2,"Human,Elf"),
("Bow","images/weapons/bow.png",1,5,"Elf"),
("Katana","images/weapons/katana.png",2,3,"Human"),
("Dirk","images/weapons/dirk.png",0,4,"Human,Elf,Dwarf"),
("Dual Wielding Axe","images/weapons/nightharv.png",5,0,"Dwarf");

