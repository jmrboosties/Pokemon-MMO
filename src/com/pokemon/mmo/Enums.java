package com.pokemon.mmo;

public class Enums {

	public static enum Types {
		NONE(0, "---"), NORMAL(1, "Normal"), FIRE(10, "Fire"), WATER(11, "Water"), ELECTRIC(13, "Electric"), GRASS(12, "Grass"), ICE(15, "Ice"), 
				FIGHTING(2, "Fighting"), POISON(4, "Poison"), GROUND(5, "Ground"), FLYING(3, "Flying"), PSYCHIC(14, "Psychic"), BUG(7, "Bug"), 
				ROCK(6, "Rock"), GHOST(8, "Ghost"), DRAGON(16, "Dragon"), DARK(17, "Dark"), STEEL(9, "Steel");

		private final int id;
		private final String name;

		private Types(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public static Types getType(int i) {
			for (Types type : Types.values()) {
				if(i == type.id) {
					return type;
				}
			}
			return NONE;
		}
		
		public int getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public static enum Ability {
		NONE(0, "---"), STENCH(1, "Stench"), DRIZZLE(2, "Drizzle"), SPEED_BOOST(3, "Speed Boost"), BATTLE_ARMOR(4, "Battle Armor"), STURDY(5, "Sturdy"), 
		DAMP(6, "Damp"), LIMBER(7, "Limber"), SAND_VEIL(8, "Sand Veil"), STATIC(9, "Static"), VOLT_ABSORB(10, "Volt Absorb"), WATER_ABSORB(11, "Water Absorb"), 
		OBLIVIOUS(12, "Oblivious"), CLOUD_NINE(13, "Cloud Nine"), COMPOUNDEYES(14, "Compoundeyes"), INSOMNIA(15, "Insomnia"), COLOR_CHANGE(16, "Color Change"), 
		IMMUNITY(17, "Immunity"), FLASH_FIRE(18, "Flash Fire"), SHIELD_DUST(19, "Shield Dust"), OWN_TEMPO(20, "Own Tempo"), SUCTION_CUPS(21, "Suction Cups"), 
		INTIMIDATE(22, "Intimidate"), SHADOW_TAG(23, "Shadow Tag"), ROUGH_SKIN(24, "Rough Skin"), WONDER_GUARD(25, "Wonder Guard"), LEVITATE(26, "Levitate"), 
		EFFECT_SPORE(27, "Effect Spore"), SYNCHRONIZE(28, "Synchronize"), CLEAR_BODY(29, "Clear Body"), NATURAL_CURE(30, "Natural Cure"), LIGHTNINGROD(31, "Lightningrod"), 
		SERENE_GRACE(32, "Serene Grace"), SWIFT_SWIM(33, "Swift Swim"), CHLOROPHYLL(34, "Chlorophyll"), ILLUMINATE(35, "Illuminate"), TRACE(36, "Trace"), 
		HUGE_POWER(37, "Huge Power"), POISON_POINT(38, "Poison Point"), INNER_FOCUS(39, "Inner Focus"), MAGMA_ARMOR(40, "Magma Armor"), WATER_VEIL(41, "Water Veil"), 
		MAGNET_PULL(42, "Magnet Pull"), SOUNDPROOF(43, "Soundproof"), RAIN_DISH(44, "Rain Dish"), SAND_STREAM(45, "Sand Stream"), PRESSURE(46, "Pressure"), 
		THICK_FAT(47, "Thick Fat"), EARLY_BIRD(48, "Early Bird"), FLAME_BODY(49, "Flame Body"), RUN_AWAY(50, "Run Away"), KEEN_EYE(51, "Keen Eye"), HYPER_CUTTER(52, "Hyper Cutter"), 
		PICKUP(53, "Pickup"), TRUANT(54, "Truant"), HUSTLE(55, "Hustle"), CUTE_CHARM(56, "Cute Charm"), PLUS(57, "Plus"), MINUS(58, "Minus"), FORECAST(59, "Forecast"), 
		STICKY_HOLD(60, "Sticky Hold"), SHED_SKIN(61, "Shed Skin"), GUTS(62, "Guts"), MARVEL_SCALE(63, "Marvel Scale"), LIQUID_OOZE(64, "Liquid Ooze"), 
		OVERGROW(65, "Overgrow"), BLAZE(66, "Blaze"), TORRENT(67, "Torrent"), SWARM(68, "Swarm"), ROCK_HEAD(69, "Rock Head"), DROUGHT(70, "Drought"), 
		ARENA_TRAP(71, "Arena Trap"), VITAL_SPIRIT(72, "Vital Spirit"), WHITE_SMOKE(73, "White Smoke"), PURE_POWER(74, "Pure Power"), SHELL_ARMOR(75, "Shell Armor"), 
		AIR_LOCK(76, "Air Lock"), TANGLED_FEET(77, "Tangled Feet"), MOTOR_DRIVE(78, "Motor Drive"), RIVALRY(79, "Rivalry"), STEADFAST(80, "Steadfast"), 
		SNOW_CLOAK(81, "Snow Cloak"), GLUTTONY(82, "Gluttony"), ANGER_POINT(83, "Anger Point"), UNBURDEN(84, "Unburden"), HEATPROOF(85, "Heatproof"), 
		SIMPLE(86, "Simple"), DRY_SKIN(87, "Dry Skin"), DOWNLOAD(88, "Download"), IRON_FIST(89, "Iron Fist"), POISON_HEAL(90, "Poison Heal"), ADAPTABILITY(91, "Adaptability"), 
		SKILL_LINK(92, "Skill Link"), HYDRATION(93, "Hydration"), SOLAR_POWER(94, "Solar Power"), QUICK_FEET(95, "Quick Feet"), NORMALIZE(96, "Normalize"), 
		SNIPER(97, "Sniper"), MAGIC_GUARD(98, "Magic Guard"), NO_GUARD(99, "No Guard"), STALL(100, "Stall"), TECHNICIAN(101, "Technician"), LEAF_GUARD(102, "Leaf Guard"), 
		KLUTZ(103, "Klutz"), MOLD_BREAKER(104, "Mold Breaker"), SUPER_LUCK(105, "Super Luck"), AFTERMATH(106, "Aftermath"), ANTICIPATION(107, "Anticipation"), 
		FOREWARN(108, "Forewarn"), UNAWARE(109, "Unaware"), TINTED_LENS(110, "Tinted Lens"), FILTER(111, "Filter"), SLOW_START(112, "Slow Start"), 
		SCRAPPY(113, "Scrappy"), STORM_DRAIN(114, "Storm Drain"), ICE_BODY(115, "Ice Body"), SOLID_ROCK(116, "Solid Rock"), SNOW_WARNING(117, "Snow Warning"), 
		HONEY_GATHER(118, "Honey Gather"), FRISK(119, "Frisk"), RECKLESS(120, "Reckless"), MULTITYPE(121, "Multitype"), FLOWER_GIFT(122, "Flower Gift"), 
		BAD_DREAMS(123, "Bad Dreams"), PICKPOCKET(124, "Pickpocket"), SHEER_FORCE(125, "Sheer Force"), CONTRARY(126, "Contrary"), UNNERVE(127, "Unnerve"), 
		DEFIANT(128, "Defiant"), DEFEATIST(129, "Defeatist"), CURSED_BODY(130, "Cursed Body"), HEALER(131, "Healer"), FRIEND_GUARD(132, "Friend Guard"), 
		WEAK_ARMOR(133, "Weak Armor"), HEAVY_METAL(134, "Heavy Metal"), LIGHT_METAL(135, "Light Metal"), MULTISCALE(136, "Multiscale"), TOXIC_BOOST(137, "Toxic Boost"), 
		FLARE_BOOST(138, "Flare Boost"), HARVEST(139, "Harvest"), TELEPATHY(140, "Telepathy"), MOODY(141, "Moody"), OVERCOAT(142, "Overcoat"), POISON_TOUCH(143, "Poison Touch"), 
		REGENERATOR(144, "Regenerator"), BIG_PECKS(145, "Big Pecks"), SAND_RUSH(146, "Sand Rush"), WONDER_SKIN(147, "Wonder Skin"), ANALYTIC(148, "Analytic"), 
		ILLUSION(149, "Illusion"), IMPOSTER(150, "Imposter"), INFILTRATOR(151, "Infiltrator"), MUMMY(152, "Mummy"), MOXIE(153, "Moxie"), JUSTIFIED(154, "Justified"), 
		RATTLED(155, "Rattled"), MAGIC_BOUNCE(156, "Magic Bounce"), SAP_SIPPER(157, "Sap Sipper"), PRANKSTER(158, "Prankster"), SAND_FORCE(159, "Sand Force"), 
		IRON_BARBS(160, "Iron Barbs"), ZEN_MODE(161, "Zen Mode"), VICTORY_STAR(162, "Victory Star"), TURBOBLAZE(163, "Turboblaze"), TERAVOLT(164, "Teravolt"); 
		
		private final int id;
		private final String name;
		
		private Ability(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public static Ability getAbility(int i) {
			for (Ability ability : Ability.values()) {
				if(i == ability.id) {
					return ability;
				}
			}
			return NONE;
		}
		
		public int getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public static enum EggGroup {
		MONSTER(1, "Monster"), WATER_1(2, "Water 1"), BUG(3, "Bug"), FLYING(4, "Flying"), GROUND(5, "Ground"), 
		FAIRY(6, "Fairy"), PLANT(7, "Plant"), HUMANSHAPE(8, "Humanshape"), WATER_3(9, "Water 3"), MINERAL(10, "Mineral"), 
		INDETERMINATE(11, "Indeterminate"), WATER_2(12, "Water 2"), DITTO(13, "Ditto"), DRAGON(14, "Dragon"), 
		NO_EGGS(15, "No Eggs");
		
		private final int fId;
		private final String fName;
		
		private EggGroup(int id, String name) {
			this.fId = id;
			this.fName = name;
		}
		
		public int getId() {
			return fId;
		}
		
		public String getName() {
			return fName;
		}
		
		public static EggGroup getEggGroup(int id) {
			for (EggGroup group : EggGroup.values()) {
				if(id == group.fId) {
					return group;
				}
			}
			return NO_EGGS;
		}

	}

	public static enum MoveKinds {
		NONE(0, "None"), PHYSICAL(1, "Physical"), SPECIAL(2, "Special"), STATUS(3, "Status");
		
		private final int fId;
		private final String fName;
		
		private MoveKinds(int i, String name) {
			this.fId = i;
			this.fName = name;
		}
		
		public static MoveKinds getMoveKind(int i) {
			for (MoveKinds kind : MoveKinds.values()) {
				if(i == kind.fId) {
					return kind;
				}
			}
			return NONE;
		}
		
		public int getId() {
			return fId;
		}
		
		public String getName() {
			return fName;
		}
		
	}

	public static enum Gender {
		GENDERLESS("Genderless"), MALE("Male"), FEMALE("Female");
		
		private final String fName;
		
		private Gender(String name) {
			this.fName = name;
		}
		
		public String getName() {
			return fName;
		}
	}

	public static enum Moves {
		SCRATCH, EMBER, TACKLE, CHARGE, ME_FIRST, EMPTY
	}

	public static enum Stats {
		HP, ATTACK, DEFENSE, SPECIAL_ATTACK, SPECIAL_DEFENSE, SPEED;
		
		public static Stats getStat(int i) {
			for (Stats stat : Stats.values()) {
				if(i == stat.ordinal()) {
					return stat;
				}
			}
			return null;
		}
	}
	
	public static enum Nature {
		NONE(0, "Natureless", Stats.HP, Stats.HP), HARDY(1, "Hardy", Stats.ATTACK, Stats.ATTACK), 
		BOLD(2, "Bold", Stats.ATTACK, Stats.DEFENSE), MODEST(3, "Modest", Stats.ATTACK, Stats.SPECIAL_ATTACK), 
		CALM(4, "Calm", Stats.ATTACK, Stats.SPECIAL_DEFENSE), TIMID(5, "Timid", Stats.ATTACK, Stats.SPEED), 
		LONELY(6, "Lonely", Stats.DEFENSE, Stats.ATTACK), DOCILE(7, "Docile", Stats.DEFENSE, Stats.DEFENSE), 
		MILD(8, "Mild", Stats.DEFENSE, Stats.SPECIAL_ATTACK), GENTLE(9, "Gentle", Stats.DEFENSE, Stats.SPECIAL_DEFENSE), 
		HASTY(10, "Hasty", Stats.DEFENSE, Stats.SPEED), ADAMANT(11, "Adamant", Stats.SPECIAL_ATTACK, Stats.ATTACK), 
		IMPISH(12, "Impish", Stats.SPECIAL_ATTACK, Stats.DEFENSE), BASHFUL(13, "Bashful", Stats.SPECIAL_ATTACK, Stats.SPECIAL_ATTACK), 
		CAREFUL(14, "Careful", Stats.SPECIAL_ATTACK, Stats.SPECIAL_DEFENSE), RASH(15, "Rash", Stats.SPECIAL_DEFENSE, Stats.SPECIAL_ATTACK), 
		JOLLY(16, "Jolly", Stats.SPECIAL_ATTACK, Stats.SPEED), NAUGHTY(17, "Naughty", Stats.SPECIAL_DEFENSE, Stats.ATTACK), 
		LAX(18, "Lax", Stats.SPECIAL_DEFENSE, Stats.DEFENSE), QUIRKY(19, "Quirky", Stats.SPECIAL_DEFENSE, Stats.SPECIAL_DEFENSE), 
		NAIVE(20, "Naive", Stats.SPECIAL_DEFENSE, Stats.SPEED), BRAVE(21, "Brave", Stats.SPEED, Stats.ATTACK), 
		RELAXED(22, "Relaxed", Stats.SPEED, Stats.DEFENSE), QUIET(23, "Quiet", Stats.SPEED, Stats.SPECIAL_ATTACK), 
		SASSY(24, "Sassy", Stats.SPEED, Stats.SPECIAL_DEFENSE), SERIOUS(25, "Serious", Stats.SPEED, Stats.SPEED);

		private final int fId;
		private final String fName;
		private final Stats fDecreased;
		private final Stats fIncreased;
		
		private Nature(int id, String name, Stats decreased, Stats increased) {
			this.fId = id;
			this.fName = name;
			this.fDecreased = decreased;
			this.fIncreased = increased;
		}
		
		public int getId() {
			return fId;
		}
		
		public String getName() {
			return fName;
		}
		
		public Stats getDecreased() {
			return fDecreased;
		}
		
		public Stats getIncreased() {
			return fIncreased;
		}
		
		public static Nature getNature(int id) {
			for (Nature nature : Nature.values()) {
				if(id == nature.fId) {
					return nature;
				}
			}
			return NONE;
		}
	}

	public static enum Weather {
		NORMAL, SUNNY_DAY, RAIN_DANCE, SANDSTORM, HAIL, HAZE
	}
	
	public static enum TeamBuff {
		
	}
	
	public static enum VolatileStatus {
		NONE,
	}

	public static enum NonVolatileStatusAilment {
		NONE, POISON, TOXIC, BURN, PARALYZE, FREEZE, SLEEP, FAINTED
	}
	
	public static enum Day {
		SUNDAY(1), MONDAY(2), TUESDAY(3), WEDNESDAY(4), THURSDAY(5), FRIDAY(6), SATURDAY(7);
		
		public final int fId;
		
		Day(int id) {
			this.fId = id;
		}
		
		public static Day getDay(int i) {
			for(Day day : Day.values()) {
				if(i == day.fId) {
					return day;
				}
			}
			throw new IllegalArgumentException("Bad int");
		}
	}

	public static enum MoveEffectGroup {
		NULL, REGULAR, EFFECT_NO_DAMAGE, ABSORB_HP, USER_FAINTS, DREAM_EATER_TYPE, MIRROR_MOVE, CHANGE_USER_STAT, IGNORES_ACC_AND_EVADE, 
		CHANGE_TARGET_STAT, RESET_IN_BATTLE_MODIFIERS, BIDE, FORCE_OPPONENT_RUN_OR_SWITCH, TWO_TO_FIVE_HITS, CHANGE_USER_TYPE, 
		VOLATILE_EFFECT_CHANCE_PLUS_DAMAGE, RECOVER_HP, PAY_DAY, ON_FIELD_WALL, STATUS_EFFECT_CHANCE_PLUS_DAMAGE, TRAP_USER_FIRE_SPIN_GROUP,
		REST, OHKO, DAMAGE_EQUALS_HALF_TARGETS_CURRENT_HP, SET_POINTS_OF_DAMAGE, INCREASED_CRIT_CHANCE, HIT_TWICE_ONE_TURN,
		IF_MISS_HALF_DMG_RECOIL, PROTECT_FROM_STAT_NERFS, INCREASE_CRIT_HIT_RATIO, RECOIL_DAMAGE, VOLATILE_EFFECT, TRANSFORM, 
		LOWER_STAT_PLUS_DAMAGE, TWO_TURN, TWO_HITS_PLUS_VOLATILE, SUBSTITUTE, IF_HIT_AFTER_USE_ON_SAME_TURN, SKETCH, METRONOME, DO_NOTHING, 
		DISABLE, ENCORE, PAIN_SPLIT, SNORE, //UP TO 87
	}

	public static enum MoveTargetId {
		SPECIFIC_TO_MOVE(1), SELECTED(2), ALLY(3), USERS_FIELD(4), USER_OR_ALLY(5), OPPONENTS_FIELD(6), USER(7), RANDOM_OPPONENT(8), 
		ALL_OTHER_POKEMON(9), SELECTED_POKEMON(10), ALL_OPPONENTS(11), ENTIRE_FIELD(12);

		public final int fId;

		private MoveTargetId(int i) {
			this.fId = i;
		}
		
		public static MoveTargetId getTarget(int i) {
			for (MoveTargetId target : MoveTargetId.values()) {
				if(i == target.fId) {
					return target;
				}
			}
			return SPECIFIC_TO_MOVE;
		}
	}
	
	public static enum MoveMetaCategory {
		INFLICTS_DAMAGE, STATUS_AILMENT_ONLY, CHANGE_STATS_ONLY, HEALS, INFLICTS_DAMAGE_AND_STATUS_AILMENT,  INFLICTS_AILMENT_AND_STAT_CHANGE,
		INFLICTS_DAMAGE_LOWER_TARGET_STATS, INFLICTS_DAMAGE_RAISES_USER_STATS, INFLICTS_AND_ABSORBS, OHKO, FULL_FIELD_EFFECT, ONE_SIDE_FIELD_EFFECT,
		FORCES_TARGET_TO_SWITCH, UNIQUE_EFFECT, MULTI_HIT, DAMAGE_AND_FORCE_SWITCH;
		
		public static MoveMetaCategory getCategory(int i) {
			for (MoveMetaCategory category : MoveMetaCategory.values()) {
				if(i == category.ordinal()) {
					return category;
				}
			}
			return UNIQUE_EFFECT;
		}
	}
	
	public static enum MetaStatusAilment {
		NONE, PARALYSIS, SLEEP, FREEZE, BURN, POISON, CONFUSION, INFATUATION, TRAPPED, NIGHTMARE, TORMENT, YAWN, HEAL_BLOCK,
		NO_TYPE_IMMUNITY, LEECH_SEED, EMBARGO, PERISH_SONG, INGRAIN, TOXIC, RISE;
		
		//TODO string for battle "is confused" "will faint in 3 turns", etc.
		
		public static MetaStatusAilment getAilment(int i) {
			for (MetaStatusAilment ailment : MetaStatusAilment.values()) {
				if(i == ailment.ordinal()) {
					return ailment;
				}
			}
			return NONE;
		}
	}
	
	public static enum MoveSecondaryNonVolatileEffect {
		NONE, POISONS, BADLY_POISONS, BURNS, PARALYZES, FREEZES, SLEEPS, TRI_ATTACK
	}
	
	public static enum VolatileEffectBatonPass {
		CONFUSION, CURSE, LEECH_SEED, LOCK_ON, PERISH_SONG, EMBARGO, HEAL_BLOCK, AQUA_RING,
		SUBSTITUTE, RISE
	}
	
	public static enum VolatileEffectNoBatonPass {
		TORMENT, TAUNT, TRAP, NIGHTMARE, INFATUATION, IDENTIFY, ENCORE, KNOCKDOWN, YAWN, INGRAIN
	}
	
	public static enum PokemonVolatileBattleStatus {
		DEFENSE_CURL, FOCUS_ENERGY, FOLLOWED, MINIMIZED
	}
	
	public static enum ModdableBattleStats {
		ATTACK("Attack"), DEFENSE("Defense"), SPECIAL_ATTACK("Special Attack"), SPECIAL_DEFENSE("Special Defense"), 
		SPEED("Speed"), ACCURACY("Accuracy"), EVASION("Evasion");
		
		private String fName;
		
		private ModdableBattleStats(String name) {
			this.fName = name;
		}
		
		public String getName() {
			return fName;
		}
		
		public static ModdableBattleStats getStat(int i) {
			for (ModdableBattleStats stat : ModdableBattleStats.values()) {
				if(i == stat.ordinal()) {
					return stat;
				}
			}
			return ATTACK;
		}
	}
	
	public static enum MoveFlag {
		MAKES_CONTACT, HAS_A_CHARGING_TURN, MUST_RECHARGE, BLOCKED_BY_PROTECT_AND_DETECT, REFLECTABLE,
		SNATCHABLE, COPIED_BY_MIRROR_MOVE, PUNCHING, SOUND_BASED, FAILS_DURING_GRAVITY, DEFROSTS_WHEN_USED,
		TARGETS_OPPOSITE_SIDE_IN_TRIPLES, HEALS, IGNORES_SUBSTITUTE, WEATHER, SPORT, ROOM;
		
		public static MoveFlag getFlag(int i) {
			i = i - 1;
			for (MoveFlag flag : MoveFlag.values()) {
				if(i == flag.ordinal()) {
					return flag;
				}
			}
			return null;
		}
	}
	
	public static enum Room {
		TRICK_ROOM, WONDER_ROOM, MAGIC_ROOM
	}
	
	public static enum Sport {
		MUD_SPORT, WATER_SPORT
	}
	
}
