package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Soldier;
import com.codecool.dungeoncrawl.logic.items.Hammer;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Potion;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String maps) {
        InputStream is = MapLoader.class.getResourceAsStream(maps);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.SKELETON);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'w':
                            cell.setType(CellType.SWORD);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case 'd' :
                            cell.setType(CellType.CLOSEDOOR);
                            break;
                        case 'g' :
                            cell.setType(CellType.FLOOR);
                            Ghost ghost = new Ghost(cell);
                            map.getGhosts().add(ghost);
                            break;
                        case 'p' :
                            cell.setType(CellType.POTION);
                            new Potion(cell);
                            break;
                        case 'v' :
                            cell.setType(CellType.FLOOR);
                            Soldier soldier = new Soldier(cell);
                            map.getSoldiers().add(soldier);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    public static GameMap loadMap2(String maps, Player player) {
        InputStream ls = MapLoader.class.getResourceAsStream(maps);
        Scanner scanner = new Scanner(ls);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(player);
                            break;
                        case 'w':
                            cell.setType(CellType.SWORD);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case 'd' :
                            cell.setType(CellType.CLOSEDOOR);
                            break;
                        case 'b' :
                            cell.setType(CellType.BACKDOOR);
                            break;
                        case 'g' :
                            cell.setType(CellType.FLOOR);
                            Ghost ghost = new Ghost(cell);
                            map.getGhosts().add(ghost);
                            break;
                        case 'p' :
                            cell.setType(CellType.POTION);
                            new Potion(cell);
                            break;
                        case 'v' :
                            cell.setType(CellType.FLOOR);
                            Soldier soldier = new Soldier(cell);
                            map.getSoldiers().add(soldier);
                            break;
                        case 'f' :
                            cell.setType(CellType.PORTAL);
                            break;
                        case 'c' :
                            cell.setType(CellType.BOSSDOOR);
                            break;
                        case 'l' :
                            cell.setType(CellType.HAMMER);
                            new Hammer(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}